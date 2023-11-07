package viewone.graphical_controllers.pt;

import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

public class AddExerciseGUIController {
    @FXML
    public void goBack() throws Exception {
        String path = "/viewone/pt/PTHome.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }
    @FXML
    public void logout() throws Exception {
        String path = "/viewone/launcher/PTLogin.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }
    @FXML
    public void addNewExercise() throws Exception {
        //TODO gestisci l'aggiunta di un esercizio
        String path = "/viewone/pt/ManageWorkoutRoutine.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }

}
