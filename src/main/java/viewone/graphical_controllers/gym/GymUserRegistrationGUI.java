package viewone.graphical_controllers.gym;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewone.GymBuddy;

import java.net.URL;
import java.util.ResourceBundle;

public class GymUserRegistrationGUI implements Initializable {

    @FXML RadioButton PersonalTrainer;
    @FXML RadioButton Athlete;
    @FXML RadioButton Male;
    @FXML RadioButton Female;

    @FXML public void goForward(ActionEvent event) throws Exception {
        String path = "/viewone/gym/GymHome.fxml";
        stampa(event, path);
    }

    @FXML public void goBack(ActionEvent event) throws Exception {
        String path = "/viewone/launcher/GymHome.fxml";
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group1 = new ToggleGroup();
        ToggleGroup group2 = new ToggleGroup();
        Male.setToggleGroup(group1);
        Female.setToggleGroup(group1);
        Athlete.setToggleGroup(group2);
        PersonalTrainer.setToggleGroup(group2);
        Athlete.fire();
    }
}
