import beans.CredentialsBean;
import beans.ExerciseBean;
import beans.SearchBean;
import beans.UserBean;
import controllers.SatisfyWorkoutRequestsController;
import controllers.UserAccessController;
import engineering.ExerciseInventory;
import engineering.LoggedUserSingleton;
import exceptions.AlreadyLoggedUserException;
import exceptions.NoUserFoundException;
import exceptions.UserCastException;
import exceptions.dataException.DataFieldException;
import model.*;
import model.record.Credentials;
import model.record.PersonalInfo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSearch {

    @Test
    public void TestSearchExercise() throws DataFieldException, NoUserFoundException, UserCastException {
        SatisfyWorkoutRequestsController controller = new SatisfyWorkoutRequestsController();
        UserAccessController controller1=new UserAccessController();

        ExerciseInventory exList = new ExerciseInventory(new ArrayList<>());

        Exercise ex1 = new Exercise("Tricep Pushdown");
        Exercise exerciseToSearch = new Exercise("Shoulder Press");
        Exercise ex3 = new Exercise("Squat", ExerciseStatus.SUSPENDED);
        Exercise ex4 = new Exercise("Dips", ExerciseStatus.SUSPENDED);

        exList.getExerciseList().add(ex1);
        exList.getExerciseList().add(exerciseToSearch);
        exList.getExerciseList().add(ex3);
        exList.getExerciseList().add(ex4);
        try {
            controller1.login(CredentialsBean.ctorWithSyntaxCheck("pt@gmail.com","forzanapule1926"));
        }catch(AlreadyLoggedUserException e){
            //LoggedUserSingleton.getSingleton().getMyBean();
        }
        SearchBean searchBean = new SearchBean(exerciseToSearch.getName());
        List<ExerciseBean> exerciseBeanList = controller.searchExercise(searchBean);

        boolean flag = exerciseBeanList.get(0).getName().equals(exerciseToSearch.getName()) ;
        if(!Objects.equals(exerciseBeanList.get(0).getName(), exerciseToSearch.getName())) {
            flag = false;
        }

        assertTrue(flag);
    }
}
