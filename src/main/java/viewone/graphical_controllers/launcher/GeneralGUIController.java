package viewone.graphical_controllers.launcher;

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
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setTitle("GymBuddy");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}
