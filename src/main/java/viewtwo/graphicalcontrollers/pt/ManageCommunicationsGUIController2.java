package viewtwo.graphicalcontrollers.pt;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageCommunicationsGUIController2 implements Initializable {
    @FXML Button buttonSendCommunicationAthlete;
    public void setVisibleAdd(Boolean bool){
        buttonSendCommunicationAthlete.setVisible(bool);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setVisibleAdd(false);
    }
}
