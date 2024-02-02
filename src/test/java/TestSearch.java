import beans.CredentialsBean;
import beans.ExerciseBean;
import beans.SearchBean;
import controllers.SatisfyWorkoutRequestsController;
import controllers.UserAccessController;
import engineering.ExerciseInventory;
import engineering.LoggedUserSingleton;
import exceptions.AlreadyLoggedUserException;
import exceptions.NoLoggedUserException;
import exceptions.NoUserFoundException;
import exceptions.UserCastException;
import exceptions.dataException.DataFieldException;
import exceptions.logger.CostumeLogger;
import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSearch {

    @Test
    public void TestSearchExercise() throws DataFieldException, NoUserFoundException {
        ExerciseInventory exList = new ExerciseInventory(new ArrayList<>());

        Exercise ex1 = new Exercise("Tricep Pushdown");
        Exercise exerciseToSearch = new Exercise("Shoulder Press");
        Exercise ex3 = new Exercise("Squat", ExerciseStatus.SUSPENDED);
        Exercise ex4 = new Exercise("Dips", ExerciseStatus.SUSPENDED);

        exList.getExerciseList().add(ex1);
        exList.getExerciseList().add(exerciseToSearch);
        exList.getExerciseList().add(ex3);
        exList.getExerciseList().add(ex4);

        UserAccessController controller1 = new UserAccessController();
        try {
            controller1.login(CredentialsBean.ctorWithSyntaxCheck("pt@gmail.com","napule"),false);
        }catch(AlreadyLoggedUserException e){
            try{
                Objects.requireNonNull(LoggedUserSingleton.getSingleton()).getMyBean();
            } catch (NullPointerException exc){
                CostumeLogger.getInstance().logError(e);

            }
        }

        SatisfyWorkoutRequestsController controller;
        try{
            controller = new SatisfyWorkoutRequestsController();
        } catch (NoLoggedUserException e){
            try {
                e.callMe(1);
                return;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        SearchBean searchBean = new SearchBean(exerciseToSearch.getName());
        List<ExerciseBean> exerciseBeanList = controller.searchExercise(searchBean);
        boolean flag = Objects.equals(exerciseBeanList.getFirst().getName(), exerciseToSearch.getName().toLowerCase());
        assertTrue(flag);
    }
}
