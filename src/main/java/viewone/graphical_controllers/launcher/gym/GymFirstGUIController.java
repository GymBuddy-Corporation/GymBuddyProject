package viewone.graphical_controllers.launcher.gym;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewone.GymBuddy;

public class GymFirstGUIController {

    @FXML
    protected void login(ActionEvent event) throws Exception {
        String path = "/viewone/launcher/GymLogin.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        stampa(root, stage);
    }

    @FXML protected void registration(ActionEvent event) throws Exception {
       String path = "/viewone/launcher/GymRegistration.fxml";
       Parent root = FXMLLoader.load(getClass().getResource(path));
       Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
       stampa(root, stage);
    }

    @FXML public void getInfo(MouseEvent event) throws Exception {
        //TODO organizza il bottone info
    }

    @FXML public void goBack(MouseEvent event) throws Exception {
        String path = "/viewone/launcher/GymFirst.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        stampa(root, stage);
    }

    public void stampa(Parent root, Stage stage) throws Exception {
        Scene scene = new Scene(root);
        stage.setTitle("GymBuddy");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
