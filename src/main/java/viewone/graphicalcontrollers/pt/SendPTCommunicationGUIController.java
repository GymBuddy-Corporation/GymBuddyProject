package viewone.graphicalcontrollers.pt;

import javafx.fxml.FXML;
import utils.SwitchPage;

public class SendPTCommunicationGUIController {
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
}
