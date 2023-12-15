package database.dao_classes;

/*import database.DatabaseConnectionSingleton;
import database.queries.ExerciseQueries;
import exceptions.DBConnectionFailedException;
import exceptions.DBUnreachableException;*/
import beans.ExerciseStatusBean;
import model.Exercise;
import model.ExerciseStatus;
import model.Gym;
import model.Trainer;
import model.record.Credentials;
import org.jetbrains.annotations.NotNull;

/*import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;*/
import java.util.ArrayList;
import java.util.List;

public class ExerciseDAO {

    private static final String NAME = "Name";
    private static final String INFO = "Info";
    private static final String IDEXERCISE = "idExercise";

    public List<Exercise> loadDBExercises(){
        List<Exercise> exList = new ArrayList<>();


        //TODO ATTENZIONE RICORDA LA VIEW NON COMUNICA CON I MODEL
        // SISTEMA BENE
        Gym gym1 = new Gym("Palestra1",
                new Credentials("gym1@gmail.com", "forzanapule1926"),
                "IBAN1112223334444", "Napoli", "Via largo Maradroga, 71","nome");
        /*Gym gym2 = new Gym("Palestra2");
        Gym gym3 = new Gym("Palestra3");
        Gym gym4 = new Gym("Palestra4");*/

        Exercise ex1 = new Exercise("Tricep Pushdown", gym1);
        Exercise ex2 = new Exercise("Shoulder Press", gym1);
        Exercise ex3 = new Exercise("Squat", gym1);
        Exercise ex4 = new Exercise("Dips", gym1);

        ex3.setStatus(ExerciseStatus.SUSPENDED);
        ex4.setStatus(ExerciseStatus.SUSPENDED);

        exList.add(ex1);
        exList.add(ex2);
        exList.add(ex3);
        exList.add(ex4);
        return exList;
    }

    public void changeExerciseStatus(Exercise exerciseToEdit, ExerciseStatus status){
        //exerciseToEdit.setStatus(status);
        // Add logic to update the exercise status in your data store (e.g., database)
    }


    public void insertExerciseInWorkoutDay(Exercise exercise, int workoutDayId) /*throws SQLException, DBUnreachableException*/ {
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                ExerciseQueries.INSERT_EXERCISE_IN_WORKOUT_DAY_QUERY)) {
            ExerciseQueries.insertExerciseInWorkoutDay(preparedStatement, exercise.getId(), workoutDayId);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/
    }

    public int saveExercise(Exercise exercise) /*throws SQLException, DBUnreachableException*/ {
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                ExerciseQueries.INSERT_EXERCISE_QUERY,
                Statement.RETURN_GENERATED_KEYS); ResultSet generatedKeys = ExerciseQueries.insertExercise(preparedStatement, exercise)){
            if(generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/
        return 0;
    }

    public List<Exercise> loadExerciseInWorkoutPlan(int idWorkoutDay, Trainer trainer) /*throws SQLException, DBUnreachableException*/ {
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                ExerciseQueries.LOAD_ALL_EXERCISE_IN_WORKOUT_DAYS_QUERY); ResultSet rs = ExerciseQueries.loadAllExerciseInWorkoutDays(preparedStatement, idWorkoutDay)){
            return getExercises(trainer, rs);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/

        //dopo togli sto null
        return null;
    }

    public List<Exercise> loadTrainerExercises(Trainer trainer) /*throws SQLException, DBUnreachableException*/ {
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                ExerciseQueries.LOAD_TRAINER_EXERCISES_QUERY); ResultSet rs = ExerciseQueries.loadTrainerExercises(trainer.getFiscalCode(), preparedStatement)){
            return getExercises(trainer, rs);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/

        //dopo togli sto null
        return null;
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
