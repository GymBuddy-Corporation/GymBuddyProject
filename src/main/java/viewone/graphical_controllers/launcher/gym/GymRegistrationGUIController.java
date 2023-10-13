package viewone.graphical_controllers.launcher.gym;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewone.GymBuddy;

public class GymRegistrationGUIController {

    @FXML private Button nextButton;
    @FXML private TextField passwField;
    @FXML private TextField passwSField;
    @FXML private TextField address;
    @FXML private TextField name;
    @FXML private TextField email;

    @FXML
    public void goForward(MouseEvent event) throws Exception {
        String path = "/viewone/gym/GymHome.fxml";
        stampa(event, path);
    }

    @FXML
    public void goBack(MouseEvent event) throws Exception {
        String path = "/viewone/launcher/GymFirst.fxml";
        stampa(event, path);
    }

    @FXML
    public void stampa(MouseEvent event, String path) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setTitle("GymBuddy");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


}
