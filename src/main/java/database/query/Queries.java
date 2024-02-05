package database.query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class Queries {
    public static final String FROM=" FROM ";
    public static final String SELECT="SELECT ";

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




    public static final String SET_EXERCISE_STATUS = "UPDATE gymbuddy.exercise " +
            "SET status = ?" +
            "WHERE nameEx = ? AND nameGym = ?";
    public static void setExerciseStatus(PreparedStatement preparedStatement, String name, String nameGym, String statusString) throws SQLException {
        preparedStatement.setString(1, statusString);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, nameGym);
        preparedStatement.executeUpdate();
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




    public static final String LOAD_ALL_WORKOUT_DAYS_QUERY = """
            SELECT wd.* 
            FROM gymbuddy.workoutDay wd 
            JOIN gymbuddy.workoutRoutines wr ON wd.athleteFC = wr.fc AND
            wd.workoutRoutineInitDate = wr.initDate
            WHERE wd.athleteFC = ?
              AND wd.workoutRoutineInitDate = ?;""";

    public static ResultSet loadAllWorkoutDays(PreparedStatement preparedStatement, String athleteFC, LocalDateTime date) throws SQLException {
        preparedStatement.setString(1, athleteFC);
        Timestamp timestamp = Timestamp.valueOf(date);
        preparedStatement.setTimestamp(2, timestamp);
        return preparedStatement.executeQuery();
    }


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

    public static void insertWorkoutRoutine(PreparedStatement preparedStatement, String name, String comment, String athleteFc) throws SQLException {
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, comment);
        preparedStatement.setString(3, athleteFc);
        preparedStatement.executeUpdate();
    }







    public static final String INSERT_WORKOUT_DAY_QUERY = "INSERT INTO gymbuddy.workoutday (nameWD, workoutRoutineInitDate, athleteFC) VALUES (?, CURDATE(), ?)";
    public static ResultSet insertWorkoutDay(PreparedStatement preparedStatement, String day, String athleteFC) throws SQLException {
        preparedStatement.setString(1, day);
        preparedStatement.setString(2, athleteFC);
        preparedStatement.executeUpdate();
        return preparedStatement.getGeneratedKeys();
    }



    public static final String REMOVE_WORKOUT_ROUTINE_QUERY = "DELETE FROM gymbuddy.workoutroutines " +
            "WHERE fc = ?";
    public static void removeWorkoutRoutine(PreparedStatement preparedStatement, String athleteFC) throws SQLException {
        preparedStatement.setString(1, athleteFC);
        preparedStatement.executeUpdate();
    }


    public static final String LOAD_USER_1_QUERY = """
            SELECT
             u.username as athleteUsername,
             u.password as athletePassword,
             pia.surname as athleteSurname,
             pia.dateofBirth as athleteDateofBirth,
             pia.birthplace as athleteBirthplace,
             pia.gender as athelteGender,
             pia.namePerson as athleteName,
             a.user_email AS athleteEmail,
             a.fc AS athleteFC,
             t.nameGym AS nameGym,
             pit.namePerson AS trainerName,
             pit.surname AS trainerSurname,
             pit.fc AS trainerFC,
             pit.dateofBirth AS traineDateOfBirth,
             pit.birthplace AS trainerBirthplace,
             pit.gender AS trainerGender,
             gi.iban AS gymIban,
             gi.city AS gymCity,
             gi.address AS gymAddress,
             gi.nameGym
            FROM gymbuddy.athlete a
            JOIN gymbuddy.personalInfo pia ON a.fc = pia.fc
            JOIN gymbuddy.trainers t ON a.trainersFC = t.fc
            JOIN gymbuddy.personalinfo pit ON t.fc = pit.fc
            JOIN gymbuddy.gymlinfo gi ON t.nameGym = gi.nameGym
            JOIN gymbuddy.user u ON a.user_email = u.email
            WHERE u.email = ?;""";



    public static ResultSet loadUser(String email, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, email);
        return preparedStatement.executeQuery();
    }


    private static final String LOAD_USER_TRAINER_QUERY = """
            SELECT
            t.email AS trainerEmail, 
            pi.namePerson AS namePerson, pi.surname AS surnamePerson, pi.dateofBirth AS dateOfBirth, pi.birthplace AS birthPlace, pi.fc AS fc, pi.gender AS gender,
            u.username AS username, u.password AS password
            FROM gymbuddy.trainers AS t JOIN gymbuddy.personalInfo AS pi ON t.fc = pi.fc JOIN gymbuddy.user AS u ON t.email = u.email""" ;

    public static final String LOAD_TRAINER_BY_FC=LOAD_USER_TRAINER_QUERY+" WHERE t.fc=?";
    public static final String LOAD_TRAINER_BY_EMAIL=LOAD_USER_TRAINER_QUERY+"WHERE t.email=?";

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


    public static final  String LOAD_TRAINER_REQUESTS_QUERY= """
            SELECT r.info, a.user_email as athleteEmail
            FROM gymbuddy.request r
            JOIN gymbuddy.trainers t ON r.trainersFC = t.fc
            JOIN gymbuddy.athlete a ON r.athleteFC = a.fc
            WHERE t.fc = ?""";


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
    public static final String LOAD_USER_GYM_BY_EMAIL_QUERY=SELECT+GYM_BASIC_INFO+FROM+GYM_TABLES+"WHERE u.email=?";
    public static  final String LOAD_ALL_GYMS=SELECT+GYM_BASIC_INFO+FROM+GYM_TABLES;
    public static final String LOAD_USER_GYM_BY_NAME_QUERRT=SELECT+GYM_BASIC_INFO+FROM+GYM_TABLES+"WHERE g.nameGym=?";

    public static final String LOAD_GYM_BY_TRAINER_FC="SELECT g.email AS gymEmail FROM "+GYM_TABLES+"JOIN gymbuddy.trainers AS t ON t.nameGym=g.nameGym WHERE t.fc=?";
    public static final String LOAD_USER_WALLET="SELECT c.athleteFC,c.starDatetMembership,c.nameGym,c.endDateMembership,c.points,c.membershipPrice,c.membershipName,c.trainers_fc FROM gymbuddy.currentmembership as  c JOIN  gymbuddy.athlete AS a ON a.fc = c.athleteFC where a.fc=?";
    public static ResultSet loadAndExecuteOneString(String string, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, string);
        return preparedStatement.executeQuery();
    }

    public static final  String LOAD_TRAINER_FC_FROM_GYM_NAME_LOWEST_ATHLETES="""
            SELECT Trainer.fc
            FROM trainers AS Trainer
            JOIN gym AS Gym ON Trainer.nameGym = Gym.nameGym
            LEFT JOIN athlete AS Athlete ON Trainer.fc = Athlete.trainersFC
            WHERE Gym.nameGym =?
            GROUP BY Trainer.fc
            ORDER BY COUNT(Athlete.fc) ASC
            LIMIT 1;""";
    protected Queries() {}
    public static final String DELETE_WALLET="DELETE FROM currentmembership WHERE athleteFC=?;";
    public static final String INSERT_WALLET="INSERT INTO currentmembership VALUES (?,?, ?,?,?,?,?,?);";
    public static int loadAndExecuteWalletInsertion(PreparedStatement preparedStatement, String athleteFc, Date startDate,String nameGym,Date endDate,int points,String nameMembership,float price,String trainerFc) throws SQLException {
            preparedStatement.setString(1,athleteFc);
            preparedStatement.setDate(2,new java.sql.Date(startDate.getTime()));
            preparedStatement.setString(3,nameGym);
            preparedStatement.setDate(4,new java.sql.Date(endDate.getTime()));
            preparedStatement.setInt(5,points);
            preparedStatement.setString(6,nameMembership);
            preparedStatement.setFloat(7,price);
            preparedStatement.setString(8,trainerFc);
            return preparedStatement.executeUpdate();

    }
}
