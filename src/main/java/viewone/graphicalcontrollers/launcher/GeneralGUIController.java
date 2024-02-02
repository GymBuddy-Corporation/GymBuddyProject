package viewone.graphicalcontrollers.launcher;

import beans.AthleteBean;
import beans.GymBean;
import beans.TrainerBean;
import beans.UserBean;
import controllers.UserAccessController;
import engineering.LoggedUserSingleton;
import exceptions.AlreadyLoggedUserException;
import exceptions.NoUserFoundException;
import utils.MainStage;
import utils.SwitchPage;
import javafx.fxml.FXML;

import java.io.IOException;

public class GeneralGUIController {

    //passaggio da hello a choose actor
    private void changePage(UserBean userBean) throws IOException {
        if (userBean instanceof AthleteBean) {
            SwitchPage.setStage(MainStage.getStage(), "AthleteHome.fxml", "athlete", 1);
        } else if (userBean instanceof TrainerBean) {
            SwitchPage.setStage(MainStage.getStage(), "PTHome.fxml", "pt", 1);
        } else if(userBean instanceof GymBean) {
            SwitchPage.setStage(MainStage.getStage(), "GymHome.fxml", "gym", 1);
        }
    }
    @FXML protected void goForward() throws IOException {
        UserAccessController controller=new UserAccessController();
        UserBean bean;
        try {
            bean=controller.loginDeserialization();
            changePage(bean);
        } catch (NoUserFoundException e) {
            SwitchPage.setStage(MainStage.getStage(),"ChooseActor.fxml","launcher",1);
        } catch (AlreadyLoggedUserException e) {
                changePage(LoggedUserSingleton.getSingleton().getMyBean());
        }

    }

}
