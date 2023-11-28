package viewone.graphicalControllers.pt;

import model.Trainer;
import utils.MainStage;
import utils.SwitchPage;
import javafx.fxml.FXML;

import java.util.Objects;


public class PTHomeGUIController {

    private String loggedTrainer;
    @FXML
    public void sendCommunication() throws Exception{
        SendPTCommunicationGUIController controller = (SendPTCommunicationGUIController) SwitchPage.setStage(MainStage.getStage(),"SendPTCommunication.fxml","pt",1);
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

    public void setValue(String trainer) {
        //TODO poi quando avremo un DB possiamo toglierla
        // e finire il tutto
        this.loggedTrainer = trainer;

    }
}
