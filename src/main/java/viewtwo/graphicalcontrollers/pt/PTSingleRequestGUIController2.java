package viewtwo.graphicalcontrollers.pt;

import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;
import viewtwo.engegnering.MainMenuSingleton;

public class PTSingleRequestGUIController2 {
    @FXML
    public void satisfyRequest() throws Exception {
        MainMenuSingleton.getMainMenu().setActivity("CreateNewWorkoutRoutine2.fxml", "pt");
    }
    @FXML
    public void goBack() throws Exception {
        MainMenuSingleton.getMainMenu().setActivity("ptHome.fxml", "pt");
    }
    @FXML
    public void rejectRequest() throws Exception {
        MainMenuSingleton.getMainMenu().setActivity("ManageCommunication.fxml", "pt");
    }

    @FXML
    public void askClarification() throws Exception {
        SwitchPage.setStage(MainStage.getStage(), "ViewYourAthletes.fxml", "pt", 1);
    }
}
