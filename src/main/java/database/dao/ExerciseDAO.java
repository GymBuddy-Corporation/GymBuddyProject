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
}
