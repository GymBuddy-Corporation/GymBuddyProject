package database.dao;

import database.SingletonConnection;
import engineering.LoggedUserSingleton;
import model.*;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public List<ExerciseForWorkoutRoutine> loadExerciseInWorkoutRoutine(String athleteFC, String workoutDayName, WorkoutRoutine workoutRoutine) {
        try(PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(
                Queries.LOAD_ALL_EXERCISE_IN_WORKOUT_DAYS_QUERY);
                ResultSet rs = Queries.loadAllExerciseInWorkoutDays(preparedStatement, athleteFC,
                        workoutDayName, workoutRoutine.getActivateDate())){
            return getExercises(workoutDayName, rs, workoutRoutine.getName());
        } catch (SQLException e) {
            SingletonConnection.closeConnection(SingletonConnection.getInstance().getConnection());
            e.printStackTrace();
            return null;
            //todo handle null
        }
    }

    @NotNull
    private ArrayList<ExerciseForWorkoutRoutine> getExercises(String workoutDayName, ResultSet rs, String workoutRoutineName) throws SQLException {
        ArrayList<ExerciseForWorkoutRoutine> exerciseList = new ArrayList<>();
        while(rs.next()){
            exerciseList.add(new ExerciseForWorkoutRoutine(
                    rs.getString("nameEx"),
                    convertStatus(rs.getString("exerciseStatus")),
                    workoutDayName,
                    rs.getInt("repetitions"),
                    rs.getInt("sets"),
                    rs.getString("rest"),
                    workoutRoutineName));
        }
        return exerciseList;
    }

    private ExerciseStatus convertStatus(String statusString){
        if(statusString.equals("ACTIVE")){
            return ExerciseStatus.ACTIVE;
        } else if(statusString.equals("SUSPENDED")){
            return ExerciseStatus.SUSPENDED;
        } else {
            throw new NullPointerException();
        }
    }

}
