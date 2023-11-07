package viewone.graphical_controllers.pt;

import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

public class SingleRequestGUIController {

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
    public void editRoutine() throws Exception {
        //TODO gestisci la modifica di una scheda già esistente
        String path = "/viewone/pt/ManageWorkoutRoutine.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }
    @FXML
    public void goHome() throws Exception {
        String path = "/viewone/pt/PTHome.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }
    public void createNewRoutine() throws Exception {
        //TODO gestisci la creazione di una nuova scheda
        String path = "/viewone/pt/ManageWorkoutRoutine.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }
    @FXML
    public void cancelRequest() throws Exception {
        //TODO gestisci la cancellazione di una richiesta
        String path = "/viewone/pt/PTHome.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }
}
