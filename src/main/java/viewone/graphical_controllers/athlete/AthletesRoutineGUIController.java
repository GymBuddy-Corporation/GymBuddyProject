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

public class AthletesRoutineGUIController implements Initializable {

    @FXML
    private ListView<String> AthletesList;

    @FXML
    public void goBack() throws Exception {
        String path = "/viewone/athlete/AthleteHome.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }
    public void logout() throws Exception {
        String path = "/viewone/launcher/AthleteLogin.fxml";
        SwitchPage.setStage(MainStage.stage,path);
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
        List<String> Lista = new ArrayList<>(Arrays.asList("13/05/2023", "23/08/2023", "05/10/2023"));
        AthletesList.getItems().addAll(Lista);
    }
}