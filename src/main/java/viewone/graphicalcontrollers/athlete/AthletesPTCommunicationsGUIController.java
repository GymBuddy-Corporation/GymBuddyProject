package viewone.graphicalcontrollers.athlete;

import controllers.UserAccessController;
import javafx.fxml.FXML;
import utils.SwitchPage;

public class AthletesPTCommunicationsGUIController {
    public static final String LAUNCHER = "launcher";
    private static final String ATHLETE = "athlete";
    private static final int VIEW=1;
    @FXML
    public void goBack() {
            SwitchPage.changePage("AthleteHome.fxml", ATHLETE,VIEW);
    }

    @FXML
    public void openCommunication(){
            //not implemented for now
            SwitchPage.changePage("Login.fxml", LAUNCHER,VIEW);
    }

    @FXML
    public void logout(){
            UserAccessController controller = new UserAccessController();
            controller.logout();
            SwitchPage.changePage( "Login.fxml", LAUNCHER, VIEW);

    }

    @FXML
    public void openProfile(){
            SwitchPage.changePage("ProfileAthlete.fxml", ATHLETE,VIEW);

    }

}
