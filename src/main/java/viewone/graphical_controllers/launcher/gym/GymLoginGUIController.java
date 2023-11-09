package viewone.graphical_controllers.launcher.gym;

import utils.MainStage;
import utils.SwitchPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewone.GymBuddy;

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
