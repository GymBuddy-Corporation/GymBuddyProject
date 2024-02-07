package database.dao;


import database.SingletonConnection;
import database.query.Queries;
import exceptions.DBUnrreachableException;
import exceptions.logger.CostumeLogger;
import model.ExerciseForWorkoutRoutine;
import model.WorkoutDay;
import model.WorkoutRoutine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WorkoutDayDAO {

    public void saveWorkoutDay(WorkoutDay workoutDay, String athleteFC) throws DBUnrreachableException {
        try(PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(
                Queries.INSERT_WORKOUT_DAY_QUERY,
                Statement.RETURN_GENERATED_KEYS)){
                Queries.insertWorkoutDay(preparedStatement, workoutDay.getDay(), athleteFC);
        } catch (SQLException e) {
            CostumeLogger.getInstance().logError(e);
            throw new DBUnrreachableException();
        }

        for (ExerciseForWorkoutRoutine exercise : workoutDay.getExerciseList()){
            new ExerciseDAO().insertExerciseInWorkoutDay(workoutDay, exercise, athleteFC);
        }
    }

    public List<WorkoutDay> loadAllWorkoutDays(String athleteFC, WorkoutRoutine workoutRoutine) throws DBUnrreachableException {
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
            CostumeLogger.getInstance().logError(e);
            throw new DBUnrreachableException();
        }
    }
}
