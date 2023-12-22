package database.query;

/*import java.sql.*;*/
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Queries {

    public static final String LOAD_TRAINER_QUERY = "SELECT * " +
            "FROM gymbuddy.Trainer " +
            "WHERE User = ?";

    public static ResultSet loadTrainer(String email, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, email);
        return preparedStatement.executeQuery();
    }

    public static ResultSet selectUserByCredentials(PreparedStatement preparedStatement, String userEmail, String password) throws SQLException {
        String query = "SELECT * FROM User WHERE email = ? AND password = ?";
        preparedStatement.setString(1, userEmail);
        preparedStatement.setString(2, password);
        return preparedStatement.executeQuery();
    }


    public static ResultSet loadUser(PreparedStatement preparedStatement, String email, String password) throws SQLException {
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        return preparedStatement.executeQuery();
    }

    public static ResultSet loadUser(String email, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, email);
        return preparedStatement.executeQuery();
    }
    public static final String LOAD_USER_2_QUERY = "SELECT " +
            "    gi.nameGym AS nameGym, gi.address AS address, gi.city AS city, gi.iban AS iban, " +
            "    g.email AS gymEmail, " +
            "    t.email AS trainerEmail, " +
            "    pi.namePerson AS namePerson, pi.surname AS surnamePerson, pi.dateofBirth AS dateOfBirth, pi.birthplace AS birthPlace, pi.fc AS fc, pi.gender AS gender, " +
            "    u.username AS username, u.password AS password " +
            "FROM " +
            "    gymbuddy.gymlInfo AS gi " +
            "JOIN " +
            "    gymbuddy.gym AS g ON gi.nameGym = g.nameGym " +
            "JOIN " +
            "    gymbuddy.trainers AS t ON g.nameGym = t.nameGym " +
            "JOIN " +
            "    gymbuddy.personalInfo AS pi ON t.fc = pi.fc " +
            "JOIN " +
            "    gymbuddy.user AS u ON t.email = u.email " +
            "WHERE " +
            "    u.email = ?";



    protected Queries() {}

}
