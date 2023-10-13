package viewone.graphical_controllers.gym;

import Nome_a_caso.MainStage;
import Nome_a_caso.SwitchPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GymUserRegistrationGUI implements Initializable {

    @FXML RadioButton PersonalTrainer;
    @FXML RadioButton Athlete;
    @FXML RadioButton Male;
    @FXML RadioButton Female;

    @FXML public void goForward(ActionEvent event) throws Exception {
        String path = "/viewone/gym/GymHome.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }

    @FXML public void goBack(ActionEvent event) throws Exception {
        String path = "/viewone/launcher/GymHome.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group1 = new ToggleGroup();
        ToggleGroup group2 = new ToggleGroup();
        Male.setToggleGroup(group1);
        Female.setToggleGroup(group1);
        Male.fire();
        Athlete.setToggleGroup(group2);
        PersonalTrainer.setToggleGroup(group2);
        Athlete.fire();

    }
}
