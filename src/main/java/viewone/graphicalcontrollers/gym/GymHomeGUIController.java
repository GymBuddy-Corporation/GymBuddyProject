package viewone.graphicalcontrollers.gym;

import controllers.UserAccessController;
import engineering.LoggedUserSingleton;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import utils.MainStage;
import utils.SwitchPage;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;

public class GymHomeGUIController implements Initializable {

    private static final String GYM="gym";
    private static final String LAUNCHER="launcher";

    private static final int VIEW=1;


    @FXML
    Text userText;

    @FXML
    public void manageAthletes()  {
        SwitchPage.changePage("GymUserView.fxml",GYM,VIEW);
    }

    @FXML
    public void managePT()  {
        SwitchPage.changePage("GymPTView.fxml",GYM,VIEW);
    }
    @FXML
    public void manageGymEquipment()  {
        SwitchPage.changePage("GymEquipmentView.fxml",GYM,VIEW);
    }
    @FXML
    public void sendCommunication()  {
        SwitchPage.changePage("SendGymCommunication.fxml",GYM,VIEW);
    }
    @FXML
    public void logout()  {
        UserAccessController controller=new UserAccessController();
        controller.logout();
        SwitchPage.changePage("Login.fxml",LAUNCHER,VIEW);
    }

    @FXML
    public void seeProfile()  {
        SwitchPage.changePage("ProfileGym.fxml",GYM,VIEW);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userText.setText(LoggedUserSingleton.getSingleton().getMyBean().getUsername());
    }
}
