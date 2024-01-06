package viewone.graphicalControllers.athlete;


import controllers.CreateRequestController;
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
        SwitchPage.setStage(MainStage.getStage(),"AthleteLogin.fxml","launcher",1);
    }
    @FXML
    public void askForNewRoutine() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //imposta la scheda associata a lui
    }
}