package database.dao;

/*import database.DatabaseConnectionSingleton;
import database.queries.TrainerQueries;
import database.queries.UserQueries;
import exceptions.DBConnectionFailedException;
import exceptions.DBUnreachableException;
import exceptions.runtime_exception.ResultSetIsNullException;*/
import database.SingletonConnection;
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

public class TrainerDAO {

    private static final String NAME = "namePerson";

    private static final String SURNAME = "surnamePerson";
    private static final String USERNAME = "Username";
    private static final String BIRTH = "dateOfBirth";
    private static final String GYM = "gymName";
    private static final String FC = "fc";
    private static final String IBAN = "iban";
    private static final String GENDER = "gender";
    private static final String EMAIL = "Email";
    private static final String PASSWORD = "Password";

    public Trainer loadTrainer(String email) throws SQLException {
        try(
             PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().
                  prepareStatement(Queries.LOAD_USER_2_QUERY);
             ResultSet rs = Queries.loadUser(email, preparedStatement)) {
            if (rs.next()) {
                PersonalInfo personalInfo = new PersonalInfo(
                        rs.getString(NAME),
                        rs.getString(SURNAME),
                        rs.getDate(BIRTH).toLocalDate(),
                        rs.getString(FC),
                        rs.getString(GENDER).charAt(0)
                );
                Credentials credentialsTrainer = new Credentials(
                        rs.getString("trainerEmail"),
                        rs.getString("password")
                );
                Gym gym = new Gym(
                        "GymUserName",
                        rs.getString("iban"),
                        rs.getString("city"),
                        rs.getString("address"),
                        rs.getString("nameGym"));
                return new Trainer(
                        rs.getString("username"),
                        personalInfo,
                        credentialsTrainer,
                        gym
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            SingletonConnection.closeConnection(SingletonConnection.getInstance().getConnection());
            System.out.println("Unreachable DB Exception.");
            //todo handle exception
            return null;
        }
    }
}