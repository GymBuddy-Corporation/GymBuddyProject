package viewone.graphicalcontrollers.pt;

import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

public class ProfilePTGUIControllerGUIController {
    private static final int VIEW=1;
    private static final String PT="pt";
    private static  final String LAUNCHER = "launcher";
    @FXML
    public void goBack()  {
        SwitchPage.changePage("PTHome.fxml",PT,VIEW);
    }
    @FXML
    public void logout()  {
        SwitchPage.changePage("Login.fxml",LAUNCHER,VIEW);
    }
    @FXML
    public void deleteAccount()  {
        SwitchPage.changePage("Login.fxml",LAUNCHER,VIEW);
    }
    @FXML
    public void editProfile()  {
        SwitchPage.changePage("ProfilePT.fxml",PT,VIEW);
    }
}
