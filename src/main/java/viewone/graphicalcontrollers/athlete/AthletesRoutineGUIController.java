package viewone.graphicalcontrollers.athlete;


import controllers.UserAccessController;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import utils.MainStage;
import utils.SwitchPage;

import java.net.URL;
import java.util.ResourceBundle;


public class AthletesRoutineGUIController implements Initializable {

    @FXML
    private ListView<String> athletesList;

    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"AthleteHome.fxml","athlete",1);
    }
    public void logout() throws Exception {
        UserAccessController controller=new UserAccessController();
        controller.logout();
        SwitchPage.setStage(MainStage.getStage(),"Login.fxml","launcher",1);
    }
    @FXML
    public void askForNewRoutine() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"AskNewRoutine.fxml","athlete",1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //todo imposta la scheda associata a lui
    }
}