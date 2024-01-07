package database.dao;


import database.SingletonConnection;
import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public List<WorkoutDay> loadAllWorkoutDays(String athleteFC, WorkoutRoutine workoutRoutine) {
        try(PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(
                Queries.LOAD_ALL_WORKOUT_DAYS_QUERY);
                ResultSet rs = Queries.loadAllWorkoutDays(preparedStatement, athleteFC, workoutRoutine.getActivateDate())){
            List<WorkoutDay> myList = new ArrayList<>();
            while(rs.next()){
                WorkoutDay workoutDay = new WorkoutDay(
                        rs.getString("nameWD"),
                        workoutRoutine.getName(),
                        new ExerciseDAO().loadExerciseInWorkoutRoutine(athleteFC,
                                rs.getString("nameWD"), workoutRoutine)
                );
                myList.add(workoutDay);
            }
            return myList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
            //todo handle exception
        }
    }
}
