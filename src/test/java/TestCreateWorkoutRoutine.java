import beans.*;
import controllers.SatisfyWorkoutRequestsController;
import controllers.UserAccessController;
import engineering.LoggedUserSingleton;
import exceptions.*;
import exceptions.dataexception.DataFieldException;
import exceptions.logger.CostumeLogger;
import model.*;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class TestCreateWorkoutRoutine extends SatisfyWorkoutRequestsController {

    public TestCreateWorkoutRoutine() throws NoLoggedUserException {}

    @Test
    void createWorkoutRoutine_shouldCreateWorkoutRoutineCorrectly() {
        //todo veifica perchè lancia NoLoggedUserException
        try {
            UserAccessController loginController = new UserAccessController();
            loginController.login(CredentialsBean.ctorWithSyntaxCheck("pt@gmail.com","Password123@"),true);
            WorkoutRoutineBean workoutRoutineBean = createBean();
            TestCreateWorkoutRoutine controller = new TestCreateWorkoutRoutine();
            WorkoutRoutine result = controller.createWorkoutRoutine(workoutRoutineBean);

            assertNotNull(result);
            assertEquals("SampleRoutine", result.getName());
            assertEquals("Sample comment", result.getComment());
            assertEquals(1, result.getWorkoutDayList().size());

            WorkoutDay createdWorkoutDay = result.getWorkoutDayList().get(0);
            assertEquals("MONDAY", createdWorkoutDay.getDay());


            ExerciseForWorkoutRoutine createdExercise = createdWorkoutDay.getExerciseList().get(0);
            assertEquals("tricep pushdown", createdExercise.getName());
            assertEquals(ExerciseStatus.ACTIVE, createdExercise.getStatus());
            assertEquals("MONDAY", createdExercise.getDay());
            assertEquals(10, createdExercise.getRepetitions());
            assertEquals(3, createdExercise.getSets());
            assertEquals("00:30", createdExercise.getRest());

        }catch(AlreadyLoggedUserException | NoUserFoundException | DataFieldException e){
            try{
                Objects.requireNonNull(LoggedUserSingleton.getSingleton()).getMyBean();
            } catch (NullPointerException exc){
                CostumeLogger.getInstance().logError(e);
            }
        } catch (DBUnrreachableException | NoLoggedUserException | SubmitRoutineException e) {
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
