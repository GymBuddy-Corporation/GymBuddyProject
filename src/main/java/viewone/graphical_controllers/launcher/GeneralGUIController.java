package viewone.graphical_controllers.launcher;

import utils.MainStage;
import utils.SwitchPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import viewone.GymBuddy;

import java.io.IOException;

public class GeneralGUIController {

    //passaggio da hello a choose actor

    @FXML protected void goForward(ActionEvent event) throws IOException {
        SwitchPage.setStage(MainStage.getStage(),"ChooseActor.fxml","launcher",1);
    }

}
