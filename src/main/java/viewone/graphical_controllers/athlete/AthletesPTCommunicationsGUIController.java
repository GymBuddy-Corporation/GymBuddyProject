package viewone.graphical_controllers.athlete;

import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

public class AthletesPTCommunicationsGUIController {

    @FXML
    public void goBack() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"AthleteHome.fxml","athlete",1);
    }

    @FXML
    public void openCommunication() throws Exception{
        //TODO apri la schermata con la comunicazione
        SwitchPage.setStage(MainStage.getStage(),"AthleteHome.fxml","athlete",1);
    }

    @FXML
    public void logout() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"AthleteLogin.fxml","launcher",1);
    }

    @FXML
    public void openProfile() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"ProfileAthlete.fxml","athlete",1);
    }

}
