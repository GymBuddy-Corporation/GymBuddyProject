package viewone.graphical_controllers.pt;

import utils.MainStage;
import utils.SwitchPage;
import javafx.fxml.FXML;


public class PTHomeGUIController {
    @FXML
    public void sendCommunication() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"SendPTCommunication.fxml","pt",1);
    }
    @FXML
    public void yourAthletes() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"ViewYourAthletes.fxml","pt",1);
    }
    @FXML
    public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(), "PTLogin.fxml", "launcher", 1);
    }

    @FXML
    public void viewRequests() throws Exception{
        SwitchPage.setStage(MainStage.getStage(), "ViewWorkoutRoutineRequests.fxml", "pt", 1);
    }

    @FXML
    public void manageCourse() throws Exception{
        SwitchPage.setStage(MainStage.getStage(), "ManageCourse.fxml", "pt", 1);
    }

}
