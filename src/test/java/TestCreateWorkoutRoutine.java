import beans.*;
import controllers.SatisfyWorkoutRequestsController;
import controllers.UserAccessController;
import engineering.ExerciseInventory;
import engineering.LoggedUserSingleton;
import exceptions.*;
import exceptions.dataException.DataFieldException;
import exceptions.logger.CostumeLogger;
import model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class TestCreateWorkoutRoutine extends SatisfyWorkoutRequestsController {

    private TestCreateWorkoutRoutine() throws NoLoggedUserException {}

    @Test
    void createWorkoutRoutine_shouldCreateWorkoutRoutineCorrectly() {

        try {
            UserAccessController loginController = new UserAccessController();
            loginController.login(CredentialsBean.ctorWithSyntaxCheck("pt@gmail.com","Password123@"),true);
        }catch(AlreadyLoggedUserException | NoUserFoundException | DataFieldException e){
            try{
                Objects.requireNonNull(LoggedUserSingleton.getSingleton()).getMyBean();
            } catch (NullPointerException exc){
                CostumeLogger.getInstance().logError(e);
            }
        } catch (DBUnrreachableException e){
            CostumeLogger.getInstance().logError(e);
        }
        try {
            WorkoutRoutineBean workoutRoutineBean = createBean();
            TestCreateWorkoutRoutine controller = new TestCreateWorkoutRoutine();
            WorkoutRoutine result = controller.createWorkoutRoutine(workoutRoutineBean);

            assertNotNull(result);
            assertEquals("SampleRoutine", result.getName());
            assertEquals("Sample comment", result.getComment());
            assertEquals(1, result.getWorkoutDayList().size());

            WorkoutDay createdWorkoutDay = result.getWorkoutDayList().get(0);
            assertEquals("Day 1", createdWorkoutDay.getDay());


            ExerciseForWorkoutRoutine createdExercise = createdWorkoutDay.getExerciseList().get(0);
            assertEquals("Push-up", createdExercise.getName());
            assertEquals(ExerciseStatus.ACTIVE, createdExercise.getStatus());
            assertEquals("Monday", createdExercise.getDay());
            assertEquals(10, createdExercise.getRepetitions());
            assertEquals(3, createdExercise.getSets());
            assertEquals("00:30", createdExercise.getRest());

        } catch (DataFieldException | SubmitRoutineException | NoLoggedUserException e) {
            CostumeLogger.getInstance().logError(e);
        }
    }

    private WorkoutRoutineBean createBean() throws SubmitRoutineException, DataFieldException {

        WorkoutRoutineBean workoutRoutineBean = new WorkoutRoutineBean();
        workoutRoutineBean.setName("SampleRoutine");
        workoutRoutineBean.setComment("Sample comment");
        WorkoutDayBean workoutDayBean = new WorkoutDayBean("MONDAY");

        ExerciseForWorkoutRoutineBean exerciseBean = new ExerciseForWorkoutRoutineBean(
                "tricep pushdown", ExerciseStatus.ACTIVE, "MONDAY", 10, 3, "00:30");

        workoutDayBean.addExerciseBean(exerciseBean);
        workoutRoutineBean.addWorkoutDayBean(workoutDayBean);
        return workoutRoutineBean;
    }
}
