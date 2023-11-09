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
import utils.MainStage;
import utils.SwitchPage;
import viewone.GymBuddy;

public class GymRegistrationGUIController {

    @FXML private Button nextButton;
    @FXML private TextField passwField;
    @FXML private TextField passwSField;
    @FXML private TextField address;
    @FXML private TextField name;
    @FXML private TextField email;

    @FXML
    public void goForward() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"GymHome.fxml","gym",1);
    }

    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"GymFirst.fxml","launcher",1);
    }




}
