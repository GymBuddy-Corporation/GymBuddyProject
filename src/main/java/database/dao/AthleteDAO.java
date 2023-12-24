package database.dao;

/*import database.DatabaseConnectionSingleton;
import database.queries.AthleteQueries;
import database.queries.UserQueries;
import exceptions.DBConnectionFailedException;
import exceptions.DBUnreachableException;
import exceptions.invalid_data_exception.ExpiredCardException;
import exceptions.runtime_exception.ResultSetIsNullException;*/
import database.SingletonConnection;
import database.query.Queries;
import model.Athlete;
import model.Gym;
import model.Trainer;
import model.record.Card;
import model.record.Credentials;
import model.record.PersonalInfo;
import org.jetbrains.annotations.Nullable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;*/

public class AthleteDAO {

    public static final String TRAINER = "Trainer";
    private static final String NAME = "namePerson";
    private static final String SURNAME = "Surname";
    private static final String USERNAME = "username";
    private static final String BIRTH = "Birth";
    private static final String FC = "FC";
    private static final String GENDER = "Gender";
    private static final String EMAIL = "Email";
    private static final String PASSWORD = "Password";
    private static final String CARD_NUMBER = "CardNumber";
    private static final String CARD_EXPIRATION_DATE = "CardExpirationDate";
    private static final String WORKOUT_PLAN = "WorkoutPlan";

    public void updateCardInfo(Card card, Athlete athlete) /*throws SQLException, DBUnreachableException*/ {
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                AthleteQueries.UPDATE_CARD_INFO_ATHLETE_QUERY)) {
            AthleteQueries.updateCardInfoAthlete(preparedStatement, athlete, card);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/

    }

    public void saveAthlete(Athlete athlete) /*throws SQLException, DBUnreachableException*/ {
       /* try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                AthleteQueries.INSERT_ATHLETE_QUERY_1);
            PreparedStatement preparedStatement1 = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                    AthleteQueries.INSERT_ATHLETE_QUERY_2)) {
            AthleteQueries.insertAthlete(preparedStatement, preparedStatement1, athlete);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/
    }

    public Athlete loadAthlete(String email) throws SQLException /*throws SQLException, DBUnreachableException*/ {
        try(
                PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().
                        prepareStatement(Queries.LOAD_USER_1_QUERY);
                ResultSet rs = Queries.loadUser(email, preparedStatement)) {
            if (rs.next()) {
                PersonalInfo personalInfoAthlete = new PersonalInfo(
                        rs.getString("trainerName"),
                        rs.getString("trainerSurname"),
                        rs.getDate("traineDateOfBirth").toLocalDate(),
                        rs.getString("athleteFC"),
                        rs.getString("athelteGender").charAt(0)
                );
                PersonalInfo personalInfoTrainer = new PersonalInfo(
                        rs.getString("athleteName"),
                        rs.getString("athleteSurname"),
                        rs.getDate("athleteDateofBirth").toLocalDate(),
                        rs.getString("trainerFC"),
                        rs.getString("trainerGender").charAt(0)
                );
                Credentials credentialsAthlete = new Credentials(
                        rs.getString("athleteEmail"),
                        rs.getString("athletePassword")
                );
                Credentials credentialsTrainer = new Credentials( //this is done for a security reason
                        "noEmail",
                        "noPassword"
                );
                Gym gym = new Gym(
                        "noUsername", //did for a security reason
                        rs.getString("gymIban"),
                        rs.getString("gymCity"),
                        rs.getString("gymAddress"),
                        rs.getString("nameGym"));
                Trainer trainer = new Trainer(
                        "noUsername",
                        personalInfoTrainer,
                        credentialsTrainer,
                        gym);
                return new Athlete(
                        rs.getString("athleteUsername"),
                        personalInfoAthlete,
                        credentialsAthlete,
                        gym,
                        trainer);
            } else {
                return null;
            }
        } catch (SQLException e) {
            SingletonConnection.closeConnection(SingletonConnection.getInstance().getConnection());
            System.out.println("Unreachable DB Exception.");
            return null;
        }
    }

    @Nullable
    private Athlete completeAthleteInfo(String fc, Athlete athlete) /*throws SQLException, DBUnreachableException, DBConnectionFailedException*/ {
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                AthleteQueries.LOAD_ATHLETE_QUERY); ResultSet rs1 = AthleteQueries.loadAthlete(preparedStatement, fc)) {
            if (rs1.next()) {
                String cardNumber = rs1.getString(CARD_NUMBER);
                Date temp = rs1.getDate(CARD_EXPIRATION_DATE);
                YearMonth cardExpirationDate = null;
                if (temp != null) {
                    cardExpirationDate = YearMonth.from(temp.toLocalDate());
                }
                athlete.setCard(new Card(cardNumber, cardExpirationDate));
                if(rs1.getString(TRAINER) != null) {
                    athlete.setTrainer(new TrainerDAO().loadTrainer(rs1.getString(TRAINER)));
                }
                if (rs1.getInt(WORKOUT_PLAN) != 0 && athlete.getTrainer() != null) {
                    athlete.setWorkoutPlan(new WorkoutRequestDAO().loadWorkoutPlan(rs1.getInt(WORKOUT_PLAN), athlete.getTrainer()));
                } else {
                    athlete.setWorkoutPlan(null);
                }
                athlete.setCourseList(new CourseDAO().loadAllCoursesAthlete(athlete));
                return athlete;
            } else {
                return null;
            }
        } catch (ExpiredCardException e) {
            try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                    AthleteQueries.REMOVE_CARD_INFO_ATHLETE_QUERY)) {
                AthleteQueries.removeCardInfoAthlete(preparedStatement, fc);
            }
            return loadAthlete(fc);
        }*/

        //dopo togli sto null
        return null;
    }

    public int getNumberOfCourses(String athleteFc) /*throws SQLException, DBUnreachableException*/ {
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                AthleteQueries.COUNT_ATHLETE_COURSES_QUERY); ResultSet rs = AthleteQueries.countAthleteCourses(preparedStatement, athleteFc)) {
            if(rs.next()){
                return rs.getInt(1);
            }else{
                throw new ResultSetIsNullException();
            }
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/

        //dopo togli sto 0
        return 0;
    }

    public void setTrainer(Athlete athlete, String fc) /*throws SQLException, DBUnreachableException*/ {
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                AthleteQueries.UPDATE_TRAINER_ATHLETE_QUERY)) {
            AthleteQueries.updateTrainerAthlete(preparedStatement, athlete.getFiscalCode(), fc);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/
    }

    public void removeTrainer(Athlete athlete) /*throws SQLException, DBUnreachableException*/ {
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                AthleteQueries.REMOVE_TRAINER_ATHLETE_QUERY)) {
            AthleteQueries.removeTrainerAthlete(preparedStatement, athlete.getFiscalCode());
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/
    }

    public void removeWorkoutPlan(int idWorkoutPlan) /*throws SQLException, DBUnreachableException*/ {
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                AthleteQueries.REMOVE_WORKOUT_PLAN_QUERY)) {
            AthleteQueries.removeWorkoutPlan(preparedStatement, idWorkoutPlan);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/
    }

    public void addWorkoutPlan(int idWorkoutPlan, String athleteFc) /*throws SQLException, DBUnreachableException*/ {
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                AthleteQueries.ADD_WORKOUT_PLAN_TO_ATHLETE_QUERY)) {
            AthleteQueries.addWorkoutPlanToAthlete(preparedStatement, idWorkoutPlan, athleteFc);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/
    }
}
