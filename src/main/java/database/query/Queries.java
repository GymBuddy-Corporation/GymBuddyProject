package database.query;

/*import java.sql.*;*/
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Queries {

    public static final String LOAD_WORKOUT_ROUTINE_QUERY =
            "SELECT wr.nameWR, wr.comment, wr.initDate" +
            "FROM gymbuddy.workoutRoutines wr" +
            "WHERE wr.fc = ?" +
            "ORDER BY wr.initDate DESC" +
            "LIMIT 1;";
    public static ResultSet loadWorkoutRoutine(PreparedStatement preparedStatement, String athleteFC) throws SQLException{
        preparedStatement.setString(1, athleteFC);
        return preparedStatement.executeQuery();
    }

    public static final String LOAD_ALL_EXERCISE_IN_WORKOUT_DAYS_QUERY =
            "SELECT we.nameEx, we.sets, we.rest, we.repetitions, we.exerciseStatus" +
                    "FROM `gymbuddy`.workoutexercise we" +
                    "WHERE we.athleteFC = ?" +
                    "AND we.workoutDayName = ?" +
                    "AND we.workoutRoutineInitDate = ?";

    public static ResultSet loadAllExerciseInWorkoutDays(PreparedStatement preparedStatement,
                                                         String athleteFC, String workoutDayName,
                                                         LocalDateTime initDate) throws SQLException {
        preparedStatement.setString(1, athleteFC);
        preparedStatement.setString(2, workoutDayName);
        Timestamp timestamp = Timestamp.valueOf(initDate);
        preparedStatement.setTimestamp(3, timestamp);
        return preparedStatement.executeQuery();
    }

    public static final String LOAD_ALL_WORKOUT_DAYS_QUERY = "SELECT wd.*\n" +
            "FROM gymbuddy.workoutDay wd\n" +
            "JOIN gymbuddy.workoutRoutines wr ON wd.athleteFC = wr.fc AND" +
            "wd.workoutRoutineInitDate = wr.initDate\n" +
            "WHERE wd.athleteFC = ?\n" +
            "  AND wd.workoutRoutineInitDate = ?;";

    public static ResultSet loadAllWorkoutDays(PreparedStatement preparedStatement, String athleteFC, LocalDateTime date) throws SQLException {
        preparedStatement.setString(1, athleteFC);
        Timestamp timestamp = Timestamp.valueOf(date);
        preparedStatement.setTimestamp(2, timestamp);
        return preparedStatement.executeQuery();
    }


    /*public static final String LOAD_ALL_EXERCISE_IN_WORKOUT_DAYS_QUERY = "SELECT Exercise.* " +
            "FROM mydb.Contains join mydb.Exercise on Contains.Exercise = Exercise.idExercise " +
            "WHERE Contains.WorkoutDay = ?";
    public static ResultSet loadAllExerciseInWorkoutDays(PreparedStatement preparedStatement, int idWorkoutDay) throws SQLException {
        preparedStatement.setInt(1, idWorkoutDay);
        return preparedStatement.executeQuery();
    }*/

    public static final String INSERT_REQUEST_QUERY = "INSERT INTO gymbuddy.request" +
            "(trainersFC, athleteFC, info, dateRequest) " +
            "VALUES (?, ?, ?, CURDATE())";
    public static void insertRequest(PreparedStatement preparedStatement, String info, String athleteFc, String trainerFc) throws SQLException {
        preparedStatement.setString(1, trainerFc);
        preparedStatement.setString(2, athleteFc);
        preparedStatement.setString(3, info);
        preparedStatement.executeUpdate();
    }

    public static final String INSERT_EXERCISE_IN_WORKOUT_DAY_QUERY = "INSERT INTO " +
            "gymbuddy.workoutexercise (sets, rest, repetitions, workoutDayName, " +
            "workoutRoutineInitDate, athleteFc, nameEx, exerciseStatus) " +
            "VALUES (?, ?, ?, ?, CURDATE(), ?, ?, ?)";
    public static void insertExerciseInWorkoutDay(PreparedStatement preparedStatement,
            int sets, String rest, int repetitions, String workoutDayName, String athleteFC,
            String nameEx, String exerciseStatus) throws SQLException {
        preparedStatement.setInt(1, sets);
        preparedStatement.setString(2, rest);
        preparedStatement.setInt(3, repetitions);
        preparedStatement.setString(4, workoutDayName);
        preparedStatement.setString(5, athleteFC);
        preparedStatement.setString(6, nameEx);
        preparedStatement.setString(7, exerciseStatus);
        preparedStatement.executeUpdate();
    }
    public static final String INSERT_WORKOUT_ROUTINE_QUERY = "INSERT INTO `gymbuddy`.`workoutroutines` (nameWR, comment, fc, initDate)" +
            "VALUES (?, ?, ?, CURDATE())";
    public static final String INSERT_WORKOUT_DAY_QUERY = "INSERT INTO gymbuddy.workoutday (nameWD, workoutRoutineInitDate, athleteFC) VALUES (?, CURDATE(), ?)";
    public static ResultSet insertWorkoutDay(PreparedStatement preparedStatement, String day, String athleteFC) throws SQLException {
        preparedStatement.setString(1, day);
        preparedStatement.setString(2, athleteFC);
        preparedStatement.executeUpdate();
        return preparedStatement.getGeneratedKeys();
    }


    public static void insertWorkoutRoutine(PreparedStatement preparedStatement, String name, String comment, String athleteFc) throws SQLException {
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, comment);
        preparedStatement.setString(3, athleteFc);
        preparedStatement.executeUpdate();
    }

    public static final String REMOVE_WORKOUT_ROUTINE_QUERY = "DELETE FROM gymbuddy.workoutroutines " +
            "WHERE fc = ?";
    public static void removeWorkoutRoutine(PreparedStatement preparedStatement, String athleteFC) throws SQLException {
        preparedStatement.setString(1, athleteFC);
        preparedStatement.executeUpdate();
    }


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


    public static final  String LOAD_GYM_EXERCISES= "SELECT e.nameEx, e.status, e.nameGym" +
            "    FROM gymbuddy.exercise e" +
            "    WHERE  e.nameGym = ?";


    public static final  String LOAD_TRAINER_REQUESTS_QUERY= "SELECT r.info, a.user_email as athleteEmail\n" +
            "    FROM gymbuddy.request r\n" +
            "    JOIN gymbuddy.trainers t ON r.trainersFC = t.fc\n" +
            "    JOIN gymbuddy.athlete a ON r.athleteFC = a.fc\n" +
            "    WHERE t.fc = ?";

    public static ResultSet loadTrainerRequests(String trainerFc, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, trainerFc);
        return preparedStatement.executeQuery();
    }
    public static final String DELETE_REQUEST_QUERY = "DELETE FROM gymbuddy.request " +
            "WHERE athleteFC = ? AND trainersFC = ?";
    public static void deleteRequest(PreparedStatement preparedStatement, String athleteFC, String trainersFC) throws SQLException {
        preparedStatement.setString(1, athleteFC);
        preparedStatement.setString(2, trainersFC);
        preparedStatement.executeUpdate();
    }

    public static ResultSet loadTrainerExercises(String trainerFc, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, trainerFc);
        return preparedStatement.executeQuery();
    }
    public static final String LOAD_USER_3_QUERY="SELECT "+GYM_BASIC_INFO+" FROM "+GYM_TABLES+"WHERE u.email=?";
    protected Queries() {}

}
