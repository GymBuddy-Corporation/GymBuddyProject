package database.dao;

/*import database.DatabaseConnectionSingleton;
import database.queries.TrainerQueries;
import database.queries.UserQueries;
import exceptions.DBConnectionFailedException;
import exceptions.DBUnreachableException;
import exceptions.runtime_exception.ResultSetIsNullException;*/
import model.Athlete;
import model.Trainer;

/*import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;*/
import java.util.ArrayList;
import java.util.List;

public class TrainerDAO {

    private static final String NAME = "Name";
    private static final String SURNAME = "Surname";
    private static final String USERNAME = "Username";
    private static final String BIRTH = "Birth";
    private static final String FC = "FC";
    private static final String GENDER = "Gender";
    private static final String EMAIL = "Email";
    private static final String PASSWORD = "Password";

    public void saveTrainer(Trainer trainer) /*throws SQLException, DBUnreachableException */{
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                TrainerQueries.INSERT_TRAINER_QUERY_1);
            PreparedStatement preparedStatement1 = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                    TrainerQueries.INSERT_TRAINER_QUERY_2)) {
            TrainerQueries.insertTrainer(preparedStatement, preparedStatement1, trainer);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/
    }

    public Trainer loadTrainer(String fc) /*throws SQLException, DBUnreachableException*/ {
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                UserQueries.LOAD_USER_2_QUERY); ResultSet rs = UserQueries.loadUser(fc, preparedStatement)) {
            if (rs.next()) {
                Trainer trainer = new Trainer(
                        rs.getString(USERNAME),
                        new PersonalInfo(
                                rs.getString(NAME),
                                rs.getString(SURNAME),
                                rs.getDate(BIRTH).toLocalDate(),
                                rs.getString(FC),
                                rs.getString(GENDER).charAt(0)
                        ),
                        new Credentials(
                                rs.getString(EMAIL),
                                rs.getString(PASSWORD)
                        )
                );
                try(PreparedStatement preparedStatement1 = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                        TrainerQueries.LOAD_TRAINER_QUERY); ResultSet rs1 = TrainerQueries.loadTrainer(fc, preparedStatement1)) {
                    if (rs1.next()) {
                        trainer.setIban(rs1.getString("Iban"));
                        return trainer;
                    } else {
                        throw new ResultSetIsNullException();
                    }
                }
            } else {
                return null;
            }
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/

        //dopo togli sto null
        return null;
    }

    public int getNumberOfSubscribers(String trainerFc) /*throws SQLException, DBUnreachableException*/ {
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                TrainerQueries.COUNT_TRAINER_SUBSCRIBERS_QUERY); ResultSet rs = TrainerQueries.countOrLoadAllTrainerSubscribers(preparedStatement, trainerFc)) {
            if(rs.next()){
                return rs.getInt(1);
            }else{
                throw new ResultSetIsNullException();
            }
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/

        //dopo togli sto 0
        return 0;
    }

    private List<Athlete> getSubscribersList(/*ResultSet rs*/) /*throws SQLException, DBUnreachableException*/ {
        List<Athlete> subscriberList = new ArrayList<>();
        /*while(rs.next()){
            Athlete subscriber = new AthleteDAO().loadAthlete(rs.getString("User"));
            subscriberList.add(subscriber);
        }*/
        return subscriberList;
    }

    private List<Trainer> getTrainersList(/*ResultSet rs*/) /*throws SQLException, DBUnreachableException*/ {
        List<Trainer> trainerList = new ArrayList<>();
        /*while(rs.next()){
            Trainer newTrainer = loadTrainer(rs.getString("User"));
            trainerList.add(newTrainer);
        }*/
        return trainerList;
    }

    public List<Trainer> searchTrainers(String name) /*throws SQLException, DBUnreachableException*/ {
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                TrainerQueries.SEARCH_TRAINER_QUERY); ResultSet rs = TrainerQueries.searchTrainer(preparedStatement, name) ){
            return getTrainersList(rs);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/

        //dopo togli sto null
        return null;
    }

    public List<Trainer> loadAllTrainers() /*throws SQLException, DBUnreachableException*/ {
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                TrainerQueries.LOAD_ALL_TRAINERS_QUERY); ResultSet rs = TrainerQueries.loadAllTrainers(preparedStatement)){
            return getTrainersList(rs);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/

        //dopo togli sto null
        return null;
    }

    public List<Athlete> loadAllTrainerSubscribers(String trainerFc) /*throws SQLException, DBUnreachableException*/ {
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                TrainerQueries.LOAD_ALL_TRAINER_SUBSCRIBERS_QUERY); ResultSet rs = TrainerQueries.countOrLoadAllTrainerSubscribers(preparedStatement, trainerFc)){
            return getSubscribersList(rs);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/

        //dopo togli sto null
        return null;
    }

    public void updateIban(String iban, Trainer trainer) /*throws SQLException, DBUnreachableException*/ {
        /*trainer.setIban(iban);
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                TrainerQueries.UPDATE_IBAN_TRAINER_QUERY)) {
            TrainerQueries.updateIbanTrainer(preparedStatement, trainer);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/
    }
}