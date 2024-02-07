package database.dao;


import database.SingletonConnection;
import database.query.Queries;
import exceptions.DBUnrreachableException;
import exceptions.logger.CostumeLogger;
import model.Request;
import model.Trainer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {
    public static final String INFO = "info";
    public static final String ATHLETEMAIL = "athleteEmail";

    public void deleteRequest(String athleteFC, String trainersFC)  {
        try(PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(
                Queries.DELETE_REQUEST_QUERY)){
            Queries.deleteRequest(preparedStatement, athleteFC, trainersFC);
        } catch (SQLException e) {
            CostumeLogger.getInstance().logError(e);

        }
    }

    public List<Request> loadTrainerRequests(Trainer trainer) throws DBUnrreachableException {
        try(PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(
                Queries.LOAD_TRAINER_REQUESTS_QUERY); ResultSet rs = Queries.loadAndExecuteOneString(trainer.getFC(), preparedStatement)){
            List<Request> myList = new ArrayList<>();
            while(rs.next()) {
                    myList.add(new Request(
                            rs.getString(INFO),
                            new AthleteDAO().loadAthlete(rs.getString(ATHLETEMAIL)),
                            trainer));

            }
            return myList;
        } catch (SQLException e) {
            throw new DBUnrreachableException();
        }
    }

    public void saveRequest( String info, String athleteFc, String trainerfc) /*throws SQLException, DBUnreachableException*/ {
        try(PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(
                Queries.INSERT_REQUEST_QUERY)){
            Queries.insertRequest(preparedStatement, info, athleteFc, trainerfc);
        } catch (SQLException e) {
            CostumeLogger.getInstance().logError(e);
        }
    }
}
