import beans.CredentialsBean;
import beans.ExerciseForWorkoutRoutineBean;
import beans.WorkoutDayBean;
import beans.WorkoutRoutineBean;
import controllers.SatisfyWorkoutRequestsController;
import controllers.UserAccessController;
import engineering.LoggedUserSingleton;
import exceptions.*;
import exceptions.dataexception.DataFieldException;
import exceptions.logger.CostumeLogger;
import model.ExerciseForWorkoutRoutine;
import model.ExerciseStatus;
import model.WorkoutDay;
import model.WorkoutRoutine;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestCreateWorkoutRoutine {

    /*Test dell'alunno Martorelli Luca.
                MATRICOLA: 0281213*/

    @Test
    void createWorkoutRoutine_shouldCreateWorkoutRoutineCorrectly () {
        try {
            UserAccessController loginController = new UserAccessController();
            loginController.login(CredentialsBean.ctorWithSyntaxCheck("pt@gmail.com", "Password123@"), true);
            WorkoutRoutineBean workoutRoutineBean = createBean();
            testController controller = new testController();
            WorkoutRoutine result = controller.create(workoutRoutineBean);

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

        } catch (AlreadyLoggedUserException | NoUserFoundException | DataFieldException e) {
            try {
                Objects.requireNonNull(LoggedUserSingleton.getSingleton()).getMyBean();
            } catch (NullPointerException exc) {
                CostumeLogger.getInstance().logError(e);
            }
        } catch (DBUnrreachableException | NoLoggedUserException | SubmitRoutineException e) {
            CostumeLogger.getInstance().logError(e);
        }
    }

    private WorkoutRoutineBean createBean () throws SubmitRoutineException, DataFieldException {

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

    static class testController extends SatisfyWorkoutRequestsController{
        public testController() throws NoLoggedUserException {
            super();
        }

        public WorkoutRoutine create(WorkoutRoutineBean workoutRoutineBean){
            return  this.createWorkoutRoutine(workoutRoutineBean);
        }

    }
}
