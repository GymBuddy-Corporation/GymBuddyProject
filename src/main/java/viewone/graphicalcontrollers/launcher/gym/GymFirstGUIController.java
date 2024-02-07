package viewone.graphicalcontrollers.launcher.gym;

import javafx.fxml.FXML;
import utils.SwitchPage;

public class GymFirstGUIController {
    private static final String LAUNCHER = "launcher";


    @FXML
    protected void login() {
        SwitchPage.changePage("Login.fxml", LAUNCHER,1);
    }


    @FXML protected void registration() {
        SwitchPage.changePage("GymRegistration.fxml", LAUNCHER,1);
    }

    @FXML public void getInfo() {
        //Not Implemented For now
    }

    @FXML public void goBack()  {
        SwitchPage.changePage("ChooseActor.fxml", LAUNCHER,1);
    }

}
