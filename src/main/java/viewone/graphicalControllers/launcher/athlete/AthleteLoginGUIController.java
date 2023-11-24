package viewone.graphicalControllers.launcher.athlete;

import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

public class AthleteLoginGUIController {

    @FXML
    public void goForward() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"AthleteHome.fxml","athlete",1);
    }

    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"ChooseActor.fxml","launcher",1);
    }

    public void getInfo() throws Exception {
        //TODO Connected with the LogoInfoButton
    }
}
