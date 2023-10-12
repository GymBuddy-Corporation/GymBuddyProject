package viewone.graphical_controllers.gym;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(GymBuddy.class.getResource("/viewone/gym/GymHome.fxml"));
        stampa(newStage, fxmlLoader);
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML public void goBack(ActionEvent event) throws Exception {
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(GymBuddy.class.getResource("/viewone/launcher/GymHome.fxml"));
        stampa(newStage, fxmlLoader);
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML public void stampa(Stage newStage, FXMLLoader fxmlLoader) throws Exception {
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        newStage.setTitle("GymBuddy");
        newStage.setResizable(false);
        newStage.setScene(scene);
        newStage.show();
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
