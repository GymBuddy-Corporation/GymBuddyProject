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

    public void saveWorkoutRoutineDays() {
    }
}
