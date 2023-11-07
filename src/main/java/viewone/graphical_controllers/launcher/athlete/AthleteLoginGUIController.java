package viewone.graphical_controllers.launcher.athlete;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import utils.MainStage;
import utils.SwitchPage;

public class AthleteLoginGUIController {

    @FXML
    public void goForward() throws Exception {
        String path = "/viewone/athlete/AthleteHome.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }

    @FXML
    public void goBack() throws Exception {
        String path = "/viewone/launcher/ChooseActor.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }


    public void getInfo() throws Exception {
        //TODO Connected with the LogoInfoButton
    }
}
