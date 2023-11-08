package viewone.graphical_controllers.athlete;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import utils.MainStage;
import utils.SwitchPage;


public class ViewOldRoutinesGUIController {

    @FXML
    private ListView<String> workoutsList;

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
    public void viewOldRoutine() {
        //TODO gestisci la visualizzazione di una vecchia scheda
    }

}