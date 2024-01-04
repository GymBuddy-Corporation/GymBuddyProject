package viewtwo.graphicalcontrollers.pt;

import viewtwo.engegnering.MainMenuSingleton;
import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

public class PTHomeGUIController2 {
    @FXML
    public void manageCommunication() throws Exception{
        MainMenuSingleton.getMainMenu().setActivity("SendPTCommunication.fxml","pt");
        SwitchPage.setStage(MainStage.getStage(),"SendPTCommunication.fxml","pt",1);
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
}
