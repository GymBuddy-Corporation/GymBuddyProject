package database.dao;


import database.SingletonConnection;
import database.query.Queries;
import exceptions.DBUnrreachableException;
import exceptions.logger.CostumeLogger;
import model.Gym;
import model.Trainer;
import model.record.Credentials;
import model.record.PersonalInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class TrainerDAO {

    private static final String NAME = "namePerson";

    private static final String SURNAME = "surnamePerson";
    private static final String USERNAME = "username";
    private static final String BIRTH = "dateOfBirth";
    private static final String GYM = "nameGym";
    private static final String FC = "fc";
    private static final String IBAN = "iban";
    private static final String GENDER = "gender";
    private static final String EMAIL = "trainerEmail";
    private static final String PASSWORD = "password";

    public Trainer loadTrainerWithAgregations(String email) throws DBUnrreachableException {
                Trainer trainer=loadTrainer(email,"email");
                if(trainer==null)return null;
                GymDAO dao=new GymDAO();
                Gym gym=dao.loadGymByTrainerFc(trainer.getFC());
                gym.setGymExercises(dao.loadDBExercises(gym.getGymName()));
                trainer.setGym(gym);
                return  trainer;
    }

    public Trainer loadTrainer(String string,String type) throws DBUnrreachableException {
        String query= Objects.equals(type, "fc") ?Queries.LOAD_TRAINER_BY_FC:Queries.LOAD_TRAINER_BY_EMAIL;
        try(
             PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(query);
             ResultSet rs = Queries.loadUser(string, preparedStatement)) {
            if (rs.next()) {
                PersonalInfo personalInfo = new PersonalInfo(
                        rs.getString(NAME),
                        rs.getString(SURNAME),
                        rs.getDate(BIRTH).toLocalDate(),
                        rs.getString(FC),
                        rs.getString(GENDER).charAt(0)
                );
                Credentials credentialsTrainer = new Credentials(
                        rs.getString(EMAIL),
                        ""
                );

                return new Trainer(
                        rs.getString(USERNAME),
                        personalInfo,
                        credentialsTrainer
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            CostumeLogger.getInstance().logError(e);
            SingletonConnection.closeConnection(SingletonConnection.getInstance().getConnection());
            throw new DBUnrreachableException();
        }
    }

    public Trainer loadTrainerToAdd(Gym gymToAdd) throws DBUnrreachableException {
                try(PreparedStatement statement = SingletonConnection.getInstance().getConnection().prepareStatement(Queries.LOAD_TRAINER_FC_FROM_GYM_NAME_LOWEST_ATHLETES)) {
                    statement.setString(1,gymToAdd.getGymName());
                    ResultSet resultSet = statement.executeQuery();
                    resultSet.next();
                    return loadTrainer(resultSet.getString("fc"), "fc");
                }catch (SQLException e){
                    CostumeLogger.getInstance().logError(e);
                    throw new DBUnrreachableException();
                }
                }
}