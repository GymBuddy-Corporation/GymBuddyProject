package viewone.graphicalcontrollers.athlete;

import controllers.UserAccessController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import utils.SwitchPage;


public class ViewOldRoutinesGUIController {

    @FXML
    private ListView<String> workoutsList;

    @FXML
    public void goBack()  {
        UserAccessController controller=new UserAccessController();
        controller.logout();
        SwitchPage.changePage("Login.fxml","launcher",1);
    }
    public void logout() {
        SwitchPage.changePage("AthleteLogin.fxml","launcher",1);
    }
    @FXML
    public void viewOldRoutine() {
            //not implemented for now
    }

}