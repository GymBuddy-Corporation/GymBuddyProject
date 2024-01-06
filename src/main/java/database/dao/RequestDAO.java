package database.dao;

/*import database.DatabaseConnectionSingleton;
import database.queries.RequestQueries;
import exceptions.DBConnectionFailedException;
import exceptions.DBUnreachableException;*/
import database.SingletonConnection;
import database.query.Queries;
import engineering.ExerciseInventory;
import model.Athlete;
import model.Gym;
import model.Request;
import model.Trainer;
import model.record.Credentials;
import model.record.PersonalInfo;

/*import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;*/
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {
    public static final String INFO = "info";
    public static final String ATHLETEMAIL = "athleteEmail";

    public void deleteRequest(String athleteFC, String trainersFC)  {
        System.out.println(athleteFC + "    " + trainersFC);
        try(PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(
                Queries.DELETE_REQUEST_QUERY)){
            Queries.deleteRequest(preparedStatement, athleteFC, trainersFC);
        } catch (SQLException e) {
            //todo handle excpetion
        }
    }

    public List<Request> loadTrainerRequests(Trainer trainer){
        try(PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(
                Queries.LOAD_TRAINER_REQUESTS_QUERY); ResultSet rs = Queries.loadTrainerRequests(trainer.getFC(), preparedStatement)){
            List<Request> myList = new ArrayList<>();
            while(rs.next()) {
                myList.add(new Request(
                        rs.getString(INFO),
                        new AthleteDAO().loadAthlete(rs.getString(ATHLETEMAIL)),
                        trainer));
            }
            return myList;
        } catch (SQLException e) {
           //TODO handle exception
            return null;
        }
    }

    public void saveRequest(LocalDateTime requestDate, String info, String athleteFc, String trainer) /*throws SQLException, DBUnreachableException*/ {
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                RequestQueries.INSERT_REQUEST_QUERY)){
            RequestQueries.insertRequest(preparedStatement, requestDate, info, athleteFc, trainer);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/
    }
}
