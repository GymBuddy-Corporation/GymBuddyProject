package viewone.graphicalcontrollers.pt;

import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

public class SendPTCommunicationGUIController {
    @FXML
    public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"GymFirst.fxml","launcher",1);
    }
    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }
    @FXML
    public void sendCommunication() throws Exception {
        //TODO manage how to send the communications
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }
}
