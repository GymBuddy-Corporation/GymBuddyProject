package database.dao;

/*import database.DatabaseConnectionSingleton;
import database.queries.TrainerQueries;
import database.queries.UserQueries;
import exceptions.DBConnectionFailedException;
import exceptions.DBUnreachableException;
import exceptions.runtime_exception.ResultSetIsNullException;*/
import database.SingletonConnection;
import exceptions.NoUserFoundException;
import model.Athlete;
import model.Gym;
import model.Trainer;
import database.query.Queries;
import model.record.Credentials;
import model.record.PersonalInfo;

/*import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;*/
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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

    public Trainer loadTrainer(String string,String type) throws SQLException {
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
            SingletonConnection.closeConnection(SingletonConnection.getInstance().getConnection());
            System.out.println(e.getErrorCode());
            //todo handle exception
            return null;
        }
    }

    public Trainer loadTrainerWithAgregations(String email) throws SQLException {
                Trainer trainer=loadTrainer(email,"email");
                GymDAO dao=new GymDAO();
                Gym gym=dao.loadGymByTrainerFc(trainer.getFC());
                gym.setGymExercises(dao.loadDBExercises(gym.getGymName()));
                trainer.setGym(gym);
                return  trainer;
    }


}