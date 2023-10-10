package viewone.graphical_controllers.launcher;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import viewone.GymBuddy;

import java.io.IOException;

public class GeneralGUIController {

    //passaggio da hello a choose actor

    @FXML protected void goForward(ActionEvent event) throws IOException {
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(GymBuddy.class.getResource("/viewone/launcher/ChooseActorView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        newStage.setTitle("GymBuddy");
        newStage.setResizable(false);
        newStage.setScene(scene);
        newStage.show();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

}
