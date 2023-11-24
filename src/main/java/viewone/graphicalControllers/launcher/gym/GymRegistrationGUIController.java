package viewone.graphicalControllers.launcher.gym;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import utils.MainStage;
import utils.SwitchPage;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class GymRegistrationGUIController implements Initializable {

    @FXML private Button nextButton;
    @FXML private TextField passwField;
    @FXML private TextField passwSField;
    @FXML private TextField address;
    @FXML private TextField name;
    @FXML private TextField email;
    @FXML private SplitMenuButton splitCityButton = new SplitMenuButton();

    @FXML
    public void goForward() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"GymHome.fxml","gym",1);
    }

    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"GymFirst.fxml","launcher",1);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Create MenuItems and add them to the SplitMenuButton
        MenuItem newItem1 = new MenuItem("New Option 1");
        MenuItem newItem2 = new MenuItem("New Option 2");
        splitCityButton.getItems().addAll(newItem1, newItem2);
    }

}
