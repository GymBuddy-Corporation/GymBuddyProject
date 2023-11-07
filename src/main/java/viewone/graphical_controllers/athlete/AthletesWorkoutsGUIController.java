package viewone.graphical_controllers.athlete;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import utils.MainStage;
import utils.SwitchPage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

//TODO SISTEMA TUTTO IL CONTROLLER

public class AthletesWorkoutsGUIController implements Initializable {

    @FXML
    private ListView<String> athletesList;

    @FXML
    public void goBack() throws Exception {
        String path = "/viewone/athlete/AthleteHome.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }
    public void logout() throws Exception {
        String path = "/viewone/launcher/AthleteLogin.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }
    @FXML
    public void askForNewRoutine() throws Exception {
        //TODO gestisci la rischiesta di una nuova scheda
    }

    @FXML
    public void modifyRoutine() throws Exception {
        //TODO gestisci la modifica di una scheda
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> lista = new ArrayList<>(Arrays.asList("13/05/2023", "23/08/2023", "05/10/2023"));
        athletesList.getItems().addAll(lista);
    }
}
