package viewone.graphical_controllers.gym;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewone.GymBuddy;

public class GymHomeGUIController {

    @FXML
    public void manageAthletes(ActionEvent event) throws Exception {
        String path = "/viewone/gym/GymUserView.fxml";
        stampa(event, path);

    }
    @FXML
    public void stampa(ActionEvent event, String path) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setTitle("GymBuddy");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }



}
