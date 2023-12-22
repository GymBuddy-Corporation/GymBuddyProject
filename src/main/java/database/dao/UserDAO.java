package database.dao;

/*import database.DatabaseConnectionSingleton;
import database.queries.UserQueries;
import exceptions.DBConnectionFailedException;
import exceptions.DBUnreachableException;
import exceptions.UserNotFoundException;
import exceptions.runtime_exception.IsNeitherATrainerNorAnAthleteException;*/
import database.SingletonConnection;
import exceptions.NoUserFoundException;
import exceptions.dataException.DataFieldException;
import model.*;
import model.record.Credentials;
import model.record.PersonalInfo;
import database.query.Queries;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;*/

public class UserDAO {

    private static final String GYM_ENUM_TYPE = "gym" ;
    private static final String TRAINER_ENUM_TYPE = "trainer" ;
    private static final String ATHLETE_ENUM_TYPE = "athlete" ;
    private static final Integer GYM_TYPE = 0 ;
    private static final Integer TRAINER_TYPE = 1 ;
    private static final Integer ATHLETE_TYPE = 2 ;

    // Constructor accepting a Connection

    private @NotNull User getUser(String username) throws SQLException/*, DBUnreachableException*/ {
        AthleteDAO aDao = new AthleteDAO();
        Athlete ret = aDao.loadAthlete(username);
        if (ret != null) {
            return ret;
        } else {
            TrainerDAO tDao = new TrainerDAO();
            Trainer ret1 = tDao.loadTrainer(username);
            if(ret1 != null) {
                System.out.println("UTENTE CORRETTAMENTE LOGGATO: " + ret1.getUsername());
                return ret1;
            }
            System.out.println("INFORMAZIONI CERCATE, NON TROVATE");
            return null; //null
            /*throw new IsNeitherATrainerNorAnAthleteException();*/
        }
    }

    public User loadUser(String email, String password) throws NoUserFoundException, DataFieldException, SQLException /*throws SQLException, DBUnreachableException, UserNotFoundException*/ {

        /*Gym palestra1 = new Gym("palestra1", new Credentials("gym@gmail.com", "forzanapule1926"),
                "BBBBBBBBBBBBBBBBBBBBBB", "roma", "Piazza dei Consoli, 11","Gym fantastic","italy");
        Trainer trainer= new Trainer("AleCortix",
                new PersonalInfo("Alessandro", "Cortese", LocalDate.now(), "CRTLSN99T24H501R", 'm'),
                new Credentials("pt@gmail.com", "forzanapule1926"), palestra1);
        Athlete athlete= new Athlete("AleCortix",
                new PersonalInfo("Alessandro", "Cortese", LocalDate.now(), "CRTLSN99T24H501R", 'm'),
                new Credentials("athlete@gmail.com", "forzanapule1926"), palestra1,trainer);

        List<User> listUsers=new ArrayList<>();
        listUsers.add(palestra1);listUsers.add(trainer);listUsers.add(athlete);

        listUsers.removeIf(p-> !Objects.equals(p.getEmail(), email));
        if(listUsers.isEmpty())throw new NoUserFoundException();
        return listUsers.getFirst();*/

        Connection connection = SingletonConnection.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM gymbuddy.user WHERE email = ? AND password = ?")) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return getUser(resultSet.getString("email")); //null
                } else {
                    System.out.println();
                    // Handle the case where the user is not found
                    System.out.println("UTENTE NON TROVATO");
                    return null;  // or throw a NoUserFoundException
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            // Handle the SQL exception
            return null;
        }

    }


    public User loadUser(String fc) /*throws SQLException, DBUnreachableException, UserNotFoundException*/ {
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                UserQueries.LOAD_USER_2_QUERY); ResultSet rs = UserQueries.loadUser(fc, preparedStatement)){
            if(rs.next()) {
                return getUser(rs.getString("FC"));
            } else {
                throw new UserNotFoundException();
            }
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/

        //dopo togli sto null
        return null;
    }

    /*private @NotNull User getUser(String fc) throws SQLException, DBUnreachableException {
        AthleteDAO aDao = new AthleteDAO();
        Athlete ret = aDao.loadAthlete(fc);
        if (ret != null) {
            return ret;
        } else {
            TrainerDAO tDao = new TrainerDAO();
            Trainer ret1 = tDao.loadTrainer(fc);
            if(ret1 != null) {
                return ret1;
            }
            throw new IsNeitherATrainerNorAnAthleteException();
        }
    }*/

    public void deleteUser(User user) /*throws SQLException, DBUnreachableException*/ {
       /* try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                UserQueries.DELETE_USER_QUERY)) {
            UserQueries.deleteUser(preparedStatement, user.getFiscalCode());
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/
    }
}
