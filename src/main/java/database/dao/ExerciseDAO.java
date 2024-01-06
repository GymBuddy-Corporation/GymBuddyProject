package database.dao;

import database.SingletonConnection;
import engineering.LoggedUserSingleton;
import model.Exercise;
import model.ExerciseForWorkoutRoutine;
import model.Trainer;
import model.WorkoutDay;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import database.query.Queries;

public class ExerciseDAO {

    private static final String NAME = "Name";
    private static final String INFO = "Info";
    private static final String IDEXERCISE = "idExercise";


    public void insertExerciseInWorkoutDay(WorkoutDay workoutDay, ExerciseForWorkoutRoutine exercise, String athleteFC) {
        String statusString = exercise.getStatus().toString();
        try(PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(
            Queries.INSERT_EXERCISE_IN_WORKOUT_DAY_QUERY)) {
            Queries.insertExerciseInWorkoutDay(preparedStatement, exercise.getSets(), exercise.getRest(),
                    exercise.getRepetitions(), workoutDay.getDay(), athleteFC, exercise.getName(),
                    statusString);
        } catch (SQLException e) {
            SingletonConnection.closeConnection(SingletonConnection.getInstance().getConnection());
            e.printStackTrace();
            //todo handle exception
        }
    }

    @NotNull
    private ArrayList<Exercise> getExercises(Trainer trainer/*, ResultSet rs*/) /*throws SQLException*/ {
        ArrayList<Exercise> exerciseList = new ArrayList<>();
       /* while(rs.next()){
            exerciseList.add(new Exercise(
                    rs.getInt(IDEXERCISE),
                    rs.getString(NAME),
                    rs.getString(INFO),
                    trainer));
        }*/
        return exerciseList;
    }

    public void removeExercise(Exercise exercise) /*throws SQLException, DBUnreachableException*/ {
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                ExerciseQueries.DELETE_EXERCISE_QUERY)){
            ExerciseQueries.deleteExercise(preparedStatement, exercise.getId());
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/
    }
}
