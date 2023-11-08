package viewone.graphical_controllers.gym;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import utils.MainStage;
import utils.SwitchPage;

import java.net.URL;
import java.util.ResourceBundle;

public class GymPTRegistrationGUI implements Initializable {

    @FXML
    RadioButton male;
    @FXML RadioButton female;

    @FXML
    public void home() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"GymHome.fxml","gym",1);
    }

    @FXML public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"GymPTView.fxml","gym",1);
    }

    @FXML public void submit() throws Exception {
        //TODO questo diventa il popup da sitemare e cliccabile solo se ogni campo è stato colmato, sennò stampa "ci sono campi incompleti".
        SwitchPage.setStage(MainStage.getStage(),"GymPTView.fxml","gym",1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group1 = new ToggleGroup();
        male.setToggleGroup(group1);
        female.setToggleGroup(group1);
        male.fire();
    }
}
