package viewone.graphicalControllers.launcher.gym;

import utils.MainStage;
import utils.SwitchPage;
import javafx.fxml.FXML;

public class GymLoginGUIController {

    @FXML
    public void goForward() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"GymHome.fxml","gym",1);
    }

    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"GymFirst.fxml","launcher",1);
    }



    public void getInfo() throws Exception {
        //TODO Connected with the LogoInfoButton
    }


}
