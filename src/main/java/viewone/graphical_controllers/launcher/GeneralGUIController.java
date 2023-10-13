package viewone.graphical_controllers.launcher;

import Nome_a_caso.MainStage;
import Nome_a_caso.SwitchPage;
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
        String path = "/viewone/launcher/ChooseActor.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }

}
