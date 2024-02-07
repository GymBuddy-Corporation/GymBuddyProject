package viewtwo.graphicalcontrollers.pt;

import exceptions.logger.CostumeLogger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import viewtwo.engegnering.MainMenuSingleton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageCommunicationsGUIController2 implements Initializable {
    @FXML Button buttonSendCommunicationAthlete;
    private void setVisibleAdd(Boolean bool){
        buttonSendCommunicationAthlete.setVisible(bool);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setVisibleAdd(false);
    }

    public void goBack() {
        try{
            PTHomeGUIController2 graphicController = (PTHomeGUIController2) MainMenuSingleton.getMainMenu().setActivity("ptHome.fxml", "pt");
            graphicController.refreshList();
        } catch (IOException e) {
            CostumeLogger.getInstance().logString("IOException in goBack");
        }
    }
}
