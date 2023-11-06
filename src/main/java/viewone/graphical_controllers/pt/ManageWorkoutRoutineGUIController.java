package viewone.graphical_controllers.pt;

import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

public class ManageWorkoutRoutineGUIController {

    @FXML
    public void goBack() throws Exception {
        String path = "/viewone/pt/PTHome.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }
    @FXML
    public void logout() throws Exception {
        String path = "/viewone/launcher/PTLogin.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }
    @FXML
    public void editExercise() throws Exception {
        //TODO gestisci la modifica di un esercizio già esistente
        String path = "/viewone/pt/PTHome.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }
    @FXML
    public void goHome() throws Exception {
        String path = "/viewone/pt/PTHome.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }
    public void addNewExercise() throws Exception {
        //TODO gestisci l'aggiunta di un esercizio
        String path = "/viewone/pt/AddExercise.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }
    @FXML
    public void cancelExercise() throws Exception {
        //TODO gestisci la cancellazione di un esercizio
        String path = "/viewone/pt/PTHome.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }
}
