package viewone.graphicalControllers.athlete;


import javafx.fxml.FXML;

import javafx.scene.control.ListView;
import utils.MainStage;
import utils.SwitchPage;


public class AthletesRoutineGUIController {

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
        //TODO gestisci la rischiesta di una nuova scheda
    }
}