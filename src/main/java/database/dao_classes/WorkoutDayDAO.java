package database.dao_classes;

/*import database.DatabaseConnectionSingleton;
import database.queries.WorkoutDayQueries;
import exceptions.DBConnectionFailedException;
import exceptions.DBUnreachableException;
import exceptions.runtime_exception.NoGeneratedKeyException;*/
import model.Exercise;
import model.Trainer;
import model.WorkoutDay;
import model.WorkoutRoutine;

/*import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;*/
import java.util.ArrayList;
import java.util.List;

public class WorkoutDayDAO {

    public void saveWorkoutDay(WorkoutDay workoutDay, int idWorkoutPlan) /*throws SQLException, DBUnreachableException*/ {
        int idWorkoutDay;
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                WorkoutDayQueries.INSERT_WORKOUT_DAY_QUERY,
                Statement.RETURN_GENERATED_KEYS); ResultSet generatedKeys = WorkoutDayQueries.insertWorkoutDay(preparedStatement, idWorkoutPlan, workoutDay.getDay())) {
            if (generatedKeys.next()) {
                idWorkoutDay = generatedKeys.getInt(1);
            } else {
                throw new NoGeneratedKeyException();
            }
            for (Exercise exercise : workoutDay.getExerciseList()){
                new ExerciseDAO().insertExerciseInWorkoutDay(exercise, idWorkoutDay);
            }
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/

    }

    public List<WorkoutDay> loadAllWorkoutDays(WorkoutRoutine workoutPlan, Trainer trainer) /*throws SQLException, DBUnreachableException*/ {
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                WorkoutDayQueries.LOAD_ALL_WORKOUT_DAYS_QUERY); ResultSet rs = WorkoutDayQueries.loadAllWorkoutDays(preparedStatement, workoutPlan.getId())){
            List<WorkoutDay> myList = new ArrayList<>();
            while(rs.next()){
                WorkoutDay workoutDay = new WorkoutDay(
                        rs.getInt("idWorkoutDay"),
                        rs.getString("Day"),
                        new ExerciseDAO().loadExerciseInWorkoutPlan(rs.getInt("idWorkoutDay"), trainer)
                );
                myList.add(workoutDay);
            }
            return myList;
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/


        //dopo togli sto null
        return null;
    }
}
