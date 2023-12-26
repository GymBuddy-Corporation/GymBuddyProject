package viewone.graphicalControllers.athlete;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import utils.MainStage;
import utils.SwitchPage;

import java.net.URL;
import java.util.ResourceBundle;

public class AskNewRoutineGUIController implements Initializable {

    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"AthletesRoutine.fxml","athlete",1);
    }
    public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"AthleteLogin.fxml","launcher",1);
    }
    public void askRequest() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"AthleteHome.fxml","launcher",1);
    }

    @FXML
    public void askForNewRoutine() {
        //TODO gestisci la rischiesta di una nuova scheda
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //imposta la scheda associata a lui
    }
}
