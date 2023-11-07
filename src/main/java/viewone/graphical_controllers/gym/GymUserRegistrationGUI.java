package viewone.graphical_controllers.gym;

import utils.MainStage;
import utils.SwitchPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class GymUserRegistrationGUI implements Initializable {

    @FXML RadioButton male;
    @FXML RadioButton female;

    @FXML public void goForward() throws Exception {
        String path = "/viewone/gym/GymHome.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }

    @FXML public void goBack() throws Exception {
        String path = "/viewone/gym/GymUserView.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group1 = new ToggleGroup();
        male.setToggleGroup(group1);
        female.setToggleGroup(group1);
        male.fire();
    }
}
