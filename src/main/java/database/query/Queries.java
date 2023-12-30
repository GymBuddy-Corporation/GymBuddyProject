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
    public static final String LOAD_USER_1_QUERY = "SELECT\n" +
            "    u.username as athleteUsername,\n" +
            "    u.password as athletePassword,\n" +
            "    pia.surname as athleteSurname,\n" +
            "    pia.dateofBirth as athleteDateofBirth,\n" +
            "    pia.birthplace as athleteBirthplace,\n" +
            "    pia.gender as athelteGender,\n" +
            "    pia.namePerson as athleteName,\n" +
            "    a.user_email AS athleteEmail,\n" +
            "    a.fc AS athleteFC,\n" +
            "    t.nameGym AS nameGym,\n" +
            "    pit.namePerson AS trainerName,\n" +
            "    pit.surname AS trainerSurname,\n" +
            "    pit.fc AS trainerFC,\n" +
            "    pit.dateofBirth AS traineDateOfBirth,\n" +
            "    pit.birthplace AS trainerBirthplace,\n" +
            "    pit.gender AS trainerGender,\n" +
            "    gi.iban AS gymIban,\n" +
            "    gi.city AS gymCity,\n" +
            "    gi.address AS gymAddress,\n" +
            "    gi.nameGym\n" +
            "FROM gymbuddy.athlete a\n" +
            "JOIN gymbuddy.personalInfo pia ON a.fc = pia.fc\n" +
            "JOIN gymbuddy.trainers t ON a.trainersFC = t.fc\n" +
            "JOIN gymbuddy.personalinfo pit ON t.fc = pit.fc\n" +
            "JOIN gymbuddy.gymlinfo gi ON t.nameGym = gi.nameGym\n" +
            "JOIN gymbuddy.user u ON a.user_email = u.email\n" +
            "WHERE u.email = ?;";

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

    private static final  String GYM_BASIC_INFO=
                            " g.email AS gymEmail," +
                            "gi.nameGym AS gymName ," +
                            "gi.address AS gymAddress," +
                            "gi.city AS gymCity," +
                            "gi.iban AS gymIban ," +
                                    "gi.country AS gymCountry, "+
                            "u.username AS gymUsername ";
    private static final String GYM_TABLES=" gymbuddy.gym AS g JOIN gymbuddy.gymlinfo AS gi ON g.nameGym=gi.nameGym JOIN gymbuddy.user AS u ON u.email=g.email ";

    public static final String LOAD_USER_3_QUERY="SELECT "+GYM_BASIC_INFO+" FROM "+GYM_TABLES+"WHERE u.email=?";
    protected Queries() {}

}
