package viewone.graphical_controllers.launcher.gym;

import Nome_a_caso.MainStage;
import Nome_a_caso.SwitchPage;
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
    public void goForward(MouseEvent event) throws Exception {
        String path = "/viewone/gym/GymHome.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }

    @FXML
    public void goBack(MouseEvent event) throws Exception {
        String path = "/viewone/launcher/GymFirst.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }



    public void getInfo(MouseEvent event) throws Exception {

    }


}
