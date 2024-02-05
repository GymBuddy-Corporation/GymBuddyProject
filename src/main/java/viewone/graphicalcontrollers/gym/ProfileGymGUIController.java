package viewone.graphicalcontrollers.gym;

import controllers.UserAccessController;
import javafx.fxml.FXML;
import utils.SwitchPage;

public class ProfileGymGUIController {
    private static final int VIEW=1;
    private static final String GYM="gym";
    private static  final String LAUNCHER = "launcher";
    @FXML
    public void goBack() {
        SwitchPage.changePage("GymHome.fxml",GYM,VIEW);
    }
    @FXML
    public void logout() {
        UserAccessController controller=new UserAccessController();
        controller.logout();
        SwitchPage.changePage("Login.fxml",LAUNCHER,VIEW);
    }
    @FXML
    public void deleteAccount()  {
        SwitchPage.changePage("GymFirst.fxml",LAUNCHER,VIEW);
    }
    @FXML
    public void editProfile() {
        SwitchPage.changePage("ProfileGym.fxml",GYM,VIEW);
    }
}
