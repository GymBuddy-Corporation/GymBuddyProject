package viewone.graphical_controllers.launcher.gym;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import viewone.GymBuddy;

import java.io.IOException;

public class GymFirstGUI {

    @FXML
    protected void login(ActionEvent event) throws IOException {
        Stage newStage = new Stage();
        //metti fxml di login
        FXMLLoader fxmlLoader = new FXMLLoader(GymBuddy.class.getResource("/viewone/launcher/chooseActor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        newStage.setTitle("GymBuddy");
        newStage.setResizable(false);
        newStage.setScene(scene);
        newStage.show();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML protected void registration(ActionEvent event) throws IOException {
        Stage newStage = new Stage();
        //metti fxml di registration
        FXMLLoader fxmlLoader = new FXMLLoader(GymBuddy.class.getResource("/viewone/launcher/chooseActor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        newStage.setTitle("GymBuddy");
        newStage.setResizable(false);
        newStage.setScene(scene);
        newStage.show();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
}
