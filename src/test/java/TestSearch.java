import beans.CredentialsBean;
import beans.ExerciseBean;
import beans.SearchBean;
import controllers.SatisfyWorkoutRequestsController;
import controllers.UserAccessController;
import engineering.ExerciseInventory;
import engineering.LoggedUserSingleton;
import exceptions.*;
import exceptions.dataexception.DataFieldException;
import exceptions.logger.CostumeLogger;
import model.Exercise;
import model.ExerciseStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestSearch {

    @Test
    void TestSearchExercise() {
        ExerciseInventory exList = new ExerciseInventory(new ArrayList<>());
        /*Test dell'alunno Martorelli Luca.
            MATRICOLA: 0281213*/
        Exercise ex1 = new Exercise("Tricep Pushdown");
        Exercise exerciseToSearch = new Exercise("Shoulder Press");
        Exercise ex3 = new Exercise("Squat", ExerciseStatus.SUSPENDED);
        Exercise ex4 = new Exercise("Dips", ExerciseStatus.SUSPENDED);

        exList.getExerciseList().add(ex1);
        exList.getExerciseList().add(exerciseToSearch);
        exList.getExerciseList().add(ex3);
        exList.getExerciseList().add(ex4);

        try {
            UserAccessController loginController = new UserAccessController();
            loginController.login(CredentialsBean.ctorWithSyntaxCheck("pt@gmail.com","Password123@"),false);
        }catch(AlreadyLoggedUserException | NoUserFoundException | DataFieldException e){
            try{
                Objects.requireNonNull(LoggedUserSingleton.getSingleton()).getMyBean();
            } catch (NullPointerException exc){
                CostumeLogger.getInstance().logError(e);
            }
        } catch (DBUnrreachableException e){
           CostumeLogger.getInstance().logError(e);
        }
        try{
            SatisfyWorkoutRequestsController controller = new SatisfyWorkoutRequestsController();
            SearchBean searchBean = new SearchBean(exerciseToSearch.getName());
            List<ExerciseBean> exerciseBeanList = controller.searchExercise(searchBean);
            boolean flag = Objects.equals(exerciseBeanList.getFirst().getName(), exerciseToSearch.getName().toLowerCase());
            assertTrue(flag);
        } catch (EmptySearchException e){
            e.callMe(1);
        } catch (NoLoggedUserException e){
            CostumeLogger.getInstance().logError(e);
        }
    }
}
