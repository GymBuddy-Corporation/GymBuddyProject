package viewone.graphicalControllers.athlete;

import controllers.UserAccessController;
import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

public class AthletesPTCommunicationsGUIController {
    private final String athlete = "athlete";

    @FXML
    public void goBack() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"AthleteHome.fxml",athlete,1);
    }

    @FXML
    public void openCommunication() throws Exception{
        //TODO apri la schermata con la comunicazione
        UserAccessController controller=new UserAccessController();
        controller.logout();
        SwitchPage.setStage(MainStage.getStage(),"Login.fxml","launcher",1);
    }

    @FXML
    public void logout() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"AthleteLogin.fxml","launcher",1);
    }

    @FXML
    public void openProfile() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"ProfileAthlete.fxml",athlete,1);
    }

}
