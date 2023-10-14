package viewone.graphical_controllers.gym;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import utils.MainStage;
import utils.SwitchPage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class GymPTGUIController implements Initializable {

    @FXML
    private ListView<String> AthletesList;

    @FXML
    public void goBack(MouseEvent event) throws Exception {
        String path = "/viewone/gym/GymHome.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }

    @FXML
    public void goForward(MouseEvent event) throws Exception {
        String path = "/viewone/gym/GymRegistrationPT.fxml"; //TODO sistema sto path con PT al posto di User
        SwitchPage.setStage(MainStage.stage,path);
    }


    @FXML
    public void addPT(MouseEvent event) throws Exception {
        String path = "/viewone/gym/GymRegistrationPT.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }

    @FXML
    public void deletePT(MouseEvent event) throws Exception {
        //TODO questo diventa il popup da sitemare e cliccabile solo se è stata scelta almeno un'opzione dalla ListView.
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> Lista = new ArrayList<>(Arrays.asList("Alex", "Nazare", "Alex Nazare"));
        AthletesList.getItems().addAll(Lista);
    }
}
