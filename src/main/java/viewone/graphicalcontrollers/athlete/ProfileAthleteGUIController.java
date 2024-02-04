package viewone.graphicalcontrollers.athlete;

import controllers.UserAccessController;
import exceptions.logger.CostumeLogger;
import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

import java.io.IOException;

public class ProfileAthleteGUIController {
    private static final String ATHLETE = "athlete";
    private static  final String LAUNCHER = "launcher";
    private static final int VIEW=1;

    @FXML
    public void goBack()  {
        SwitchPage.changePage("AthleteHome.fxml",ATHLETE,VIEW);
    }
    @FXML
    public void logout() {
        UserAccessController controller=new UserAccessController();
        controller.logout();
        SwitchPage.changePage("Login.fxml",LAUNCHER,VIEW);
    }
    @FXML
    public void deleteAccount()  {
        SwitchPage.changePage("AthleteLogin.fxml",LAUNCHER,VIEW);
    }
    @FXML
    public void editProfile()  {
        SwitchPage.changePage("AthletePT.fxml",ATHLETE,VIEW);
    }
}
