package viewone.graphicalcontrollers.pt;

import controllers.UserAccessController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import utils.SwitchPage;

import java.net.URL;
import java.util.ResourceBundle;

public class SendPTCommunicationGUIController implements Initializable {

    @FXML private Text usernameText;
    @FXML
    public void logout() {
        SwitchPage.changePage("GymFirst.fxml","launcher",1);
    }
    @FXML
    public void goBack()  {
        SwitchPage.changePage("PTHome.fxml","pt",1);
    }
    @FXML
    public void sendCommunication() {
        //not implemented
        SwitchPage.changePage("PTHome.fxml","pt",1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameText.setText(new UserAccessController().getUser().getUsername());
    }
}
