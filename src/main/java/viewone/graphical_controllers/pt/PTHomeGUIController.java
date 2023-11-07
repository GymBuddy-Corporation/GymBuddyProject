package viewone.graphical_controllers.pt;

import utils.MainStage;
import utils.SwitchPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PTHomeGUIController {
    @FXML
    public void sendCommunication(ActionEvent event) throws Exception {
        String path = "/viewone/pt/SendPTCommunication.fxml";
        SwitchPage.setStage(MainStage.getStage(), path);
    }
    @FXML
    public void yourAthletes(ActionEvent event) throws Exception {
        String path = "/viewone/pt/ViewYourAthletes.fxml";
        SwitchPage.setStage(MainStage.getStage(), path);
    }
    @FXML
    public void logout() throws Exception {
        String path = "/viewone/launcher/PTLogin.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }
    @FXML
    public void manageWorkoutRoutines(ActionEvent event) throws Exception {
        String path="/viewone/pt/ManageWorkoutRoutines.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }

    @FXML
    public void viewRequests(ActionEvent event) throws Exception {
        String path="/viewone/pt/ViewWorkoutRoutineRequests.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }


}
