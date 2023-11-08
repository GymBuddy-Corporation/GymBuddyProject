package viewone.graphical_controllers.gym;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import utils.MainStage;
import utils.SwitchPage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class GymPTGUIController implements Initializable {

    @FXML
    private ListView<String> athletesList;

    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"GymHome.fxml","gym",1);
    }

    public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"GymFirst.fxml","launcher",1);
    }

    @FXML
    public void goForward() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"GymRegistrationPT.fxml","gym",1);
    }


    @FXML
    public void addPT() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"GymRegistrationPT.fxml","gym",1);
    }

    @FXML
    public void deletePT() throws Exception {
        //TODO questo diventa il popup da sitemare e cliccabile solo se è stata scelta almeno un'opzione dalla ListView.
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> lista = new ArrayList<>(Arrays.asList("Alex", "Nazare", "Alex Nazare"));
        athletesList.getItems().addAll(lista);
    }
}
