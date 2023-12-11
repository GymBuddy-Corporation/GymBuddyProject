package database.dao_classes;

/*import database.DatabaseConnectionSingleton;
import database.queries.WorkoutPlanQueries;
import exceptions.DBConnectionFailedException;
import exceptions.DBUnreachableException;
import exceptions.runtime_exception.NoGeneratedKeyException;*/
import model.Trainer;
import model.WorkoutDay;
import model.WorkoutRoutine;

/*import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;*/

public class WorkoutRequestDAO {

    public void saveWorkoutPlan(WorkoutRoutine workoutPlan, String athleteFc) /*throws SQLException, DBUnreachableException*/ {
        int idWorkoutPlan;
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                WorkoutPlanQueries.INSERT_WORKOUT_PLAN_QUERY,
                Statement.RETURN_GENERATED_KEYS); ResultSet generatedKeys = WorkoutPlanQueries.insertWorkoutPlan(preparedStatement, athleteFc)) {
            if (generatedKeys.next()) {
                idWorkoutPlan = generatedKeys.getInt(1);
            } else {
                throw new NoGeneratedKeyException();
            }
            for (WorkoutDay workoutDay : workoutPlan.getWorkoutDayList()) {
                new WorkoutDayDAO().saveWorkoutDay(workoutDay, idWorkoutPlan);
            }
            new AthleteDAO().addWorkoutPlan(idWorkoutPlan, athleteFc);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/
    }

    public WorkoutRoutine loadWorkoutPlan(Integer idWorkoutPlan, Trainer trainer) /*throws SQLException, DBUnreachableException*/ {
        /*WorkoutRoutine workoutPlan = new WorkoutRoutine(idWorkoutPlan);
        workoutPlan.addAllWorkoutDays(new WorkoutDayDAO().loadAllWorkoutDays(workoutPlan, trainer));*/
        return null;//workoutPlan;
    }
}

