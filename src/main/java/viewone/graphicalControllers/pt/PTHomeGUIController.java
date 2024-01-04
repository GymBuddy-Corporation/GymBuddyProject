package viewone.graphicalControllers.pt;

import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import model.Trainer;
import utils.MainStage;
import utils.SwitchPage;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class PTHomeGUIController {

    @FXML private Text usernameText;
    @FXML
    public void sendCommunication() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"ManageCommunication.fxml","pt",1);
        //TODO caso d'uso
        //Objects.requireNonNull(controller).setValue(loggedTrainer, sendCommunicationController);
    }
    @FXML
    public void yourAthletes() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"ViewYourAthletes.fxml","pt",1);
    }
    @FXML
    public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(), "Login.fxml", "launcher", 1);
    }

    @FXML
    public void viewRequests() throws Exception{
        SwitchPage.setStage(MainStage.getStage(), "ViewWorkoutRoutineRequests.fxml", "pt", 1);
    }

    @FXML
    public void manageCourse() throws Exception{
        SwitchPage.setStage(MainStage.getStage(), "ManageCourse.fxml", "pt", 1);
    }

    public void setValue() {
        //TODO poi quando avremo un DB possiamo toglierla
        // e finire il tutto
        /*this.loggedTrainer = trainer;
        usernameText.setText(loggedTrainer.getUsername());*/
    }
}
