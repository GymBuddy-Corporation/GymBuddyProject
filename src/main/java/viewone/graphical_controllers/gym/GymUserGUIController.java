package viewone.graphical_controllers.gym;

import utils.MainStage;
import utils.SwitchPage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class GymUserGUIController implements Initializable {

    @FXML private ListView<String> athletesList;

    @FXML
    public void goBack(MouseEvent event) throws Exception {
        String path = "/viewone/gym/GymHome.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }

    public void logout() throws Exception {
        String path = "/viewone/launcher/GymFirst.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }

    @FXML
    public void addAthlete(MouseEvent event) throws Exception {
        String path = "/viewone/gym/GymRegistrationUser.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> Lista = new ArrayList<>(Arrays.asList("Luca", "Matteo", "Marco"));
        athletesList.getItems().addAll(Lista);
    }
}
