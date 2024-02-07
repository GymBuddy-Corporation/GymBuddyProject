package database.dao;

import database.SingletonConnection;
import database.query.Queries;
import exceptions.DBUnrreachableException;
import exceptions.logger.CostumeLogger;
import model.*;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDAO {

    public void insertExerciseInWorkoutDay(WorkoutDay workoutDay, ExerciseForWorkoutRoutine exercise, String athleteFC) {
        String statusString = exercise.getStatus().toString();
        try(PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(
            Queries.INSERT_EXERCISE_IN_WORKOUT_DAY_QUERY)) {
            preparedStatement.setInt(1, exercise.getSets());
            preparedStatement.setString(2, exercise.getRest());
            preparedStatement.setInt(3, exercise.getRepetitions());
            preparedStatement.setString(4, workoutDay.getDay());
            preparedStatement.setString(5, athleteFC);
            preparedStatement.setString(6, exercise.getName());
            preparedStatement.setString(7, statusString);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            SingletonConnection.closeConnection(SingletonConnection.getInstance().getConnection());
            CostumeLogger.getInstance().logError(e);
            CostumeLogger.getInstance().logError(e);
        }
    }

    public List<ExerciseForWorkoutRoutine> loadExerciseInWorkoutRoutine(String athleteFC, String workoutDayName, WorkoutRoutine workoutRoutine) throws DBUnrreachableException {
        try(PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(
                Queries.LOAD_ALL_EXERCISE_IN_WORKOUT_DAYS_QUERY);
                ResultSet rs = Queries.loadAllExerciseInWorkoutDays(preparedStatement, athleteFC,
                        workoutDayName, workoutRoutine.getActivateDate())){
            return getExercises(workoutDayName, rs, workoutRoutine.getName());
        } catch (SQLException e) {
            SingletonConnection.closeConnection(SingletonConnection.getInstance().getConnection());
            CostumeLogger.getInstance().logError(e);
            throw new DBUnrreachableException();
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

    public void setExerciseStatus(Exercise exercise, String nameGym){
        String statusString = exercise.getStatus().toString();
        try(PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(
                Queries.SET_EXERCISE_STATUS)) {
            Queries.setExerciseStatus(preparedStatement, exercise.getName(), nameGym, statusString);
        } catch (SQLException e) {
            SingletonConnection.closeConnection(SingletonConnection.getInstance().getConnection());
            e.printStackTrace();
        }
    }

}
