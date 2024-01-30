package database.dao;


import database.SingletonConnection;
import database.query.Queries;
import exceptions.DBUnrreachableException;
import model.Request;
import model.Trainer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            e.printStackTrace();
        }
    }

    public List<Request> loadTrainerRequests(Trainer trainer){
        try(PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(
                Queries.LOAD_TRAINER_REQUESTS_QUERY); ResultSet rs = Queries.loadTrainerRequests(trainer.getFC(), preparedStatement)){
            List<Request> myList = new ArrayList<>();
            extractRequests(trainer, rs, myList);
            return myList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void extractRequests(Trainer trainer, ResultSet rs, List<Request> myList) throws SQLException {
        while(rs.next()) {
            try {
                myList.add(new Request(
                        rs.getString(INFO),
                        new AthleteDAO().loadAthlete(rs.getString(ATHLETEMAIL)),
                        trainer));
            } catch (DBUnrreachableException ignore){}
        }
    }

    public void saveRequest(LocalDateTime requestDate, String info, String athleteFc, String trainerfc) /*throws SQLException, DBUnreachableException*/ {
        try(PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(
                Queries.INSERT_REQUEST_QUERY)){
            Queries.insertRequest(preparedStatement, info, athleteFc, trainerfc);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
