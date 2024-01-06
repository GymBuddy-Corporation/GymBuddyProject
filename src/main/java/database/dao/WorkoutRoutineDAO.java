package database.dao;

import database.SingletonConnection;
import model.WorkoutDay;
import model.WorkoutRoutine;

import java.sql.SQLException;

import database.query.Queries;
import java.sql.PreparedStatement;

public class WorkoutRoutineDAO {
    public void saveWorkoutRoutine(WorkoutRoutine workoutRoutine, String athleteFc) {
        try (PreparedStatement preparedStatement1 = SingletonConnection.getInstance().getConnection().prepareStatement(
                Queries.INSERT_WORKOUT_ROUTINE_QUERY)) {
            Queries.insertWorkoutRoutine(preparedStatement1, workoutRoutine.getName(), workoutRoutine.getComment(), athleteFc);
        } catch (SQLException e) {
            e.printStackTrace();
            //todo handle exception
        }
        for (WorkoutDay workoutDay : workoutRoutine.getWorkoutDayList()) {
            new WorkoutDayDAO().saveWorkoutDay(workoutDay, athleteFc);
        }
    }
}
