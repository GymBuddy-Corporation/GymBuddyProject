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

    private @NotNull User getUser(String username) throws SQLException, NoUserFoundException {
        AthleteDAO aDao = new AthleteDAO();
        Athlete ret = aDao.loadAthlete(username);
        if (ret != null)return ret;
        TrainerDAO tDao = new TrainerDAO();
        Trainer ret1 = tDao.loadTrainer(username);
        if(ret1 != null)return ret1;
        GymDAO gDao=new GymDAO();
        //aggiungere l'eccezione alle altre e circondarle da un try catch
        return gDao.loadGym(username);
    }

    public User loadUser(String email, String password) throws SQLException,NoUserFoundException  {
        Connection connection = SingletonConnection.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM gymbuddy.user WHERE email = ? AND password = ?")) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return getUser(resultSet.getString("email")); //null //???
                } else {
                    throw new NoUserFoundException();
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            // Handle the SQL exception, throware una nuova eccezioen dedicata per sql
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


}
