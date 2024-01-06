package database.dao;


import database.SingletonConnection;
import model.*;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import database.query.Queries;
import java.sql.PreparedStatement;

public class WorkoutDayDAO {

    public void saveWorkoutDay(WorkoutDay workoutDay, String athleteFC)  {
        try(PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(
                Queries.INSERT_WORKOUT_DAY_QUERY,
                Statement.RETURN_GENERATED_KEYS)){
                Queries.insertWorkoutDay(preparedStatement, workoutDay.getDay(), athleteFC);
        } catch (SQLException e) {
            e.printStackTrace();
            //todo handle exception
        }

        for (ExerciseForWorkoutRoutine exercise : workoutDay.getExerciseList()){
            new ExerciseDAO().insertExerciseInWorkoutDay(workoutDay, exercise, athleteFC);
        }
    }
}
