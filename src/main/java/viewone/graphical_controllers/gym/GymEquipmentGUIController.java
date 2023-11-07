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

public class GymEquipmentGUIController implements Initializable {

    @FXML
    private ListView<String> athletesList;

    @FXML
    public void goBack(MouseEvent event) throws Exception {
        String path = "/viewone/gym/GymHome.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }

    @FXML
    public void goForward(MouseEvent event) throws Exception {
        String path = "/viewone/gym/GymRegistrationUser.fxml"; //TODO sistema sto path con Equipment al posto di User
        SwitchPage.setStage(MainStage.getStage(),path);
    }


    @FXML
    public void addEquipment(MouseEvent event) throws Exception {
        String path = "/viewone/gym/GymRegistrationUser.fxml"; //TODO sistema sto path con Equipment al posto di User
        SwitchPage.setStage(MainStage.getStage(),path);
    }

    public void logout() throws Exception {
        String path = "/viewone/launcher/GymFirst.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> lista = new ArrayList<>(Arrays.asList("Panca Piana", "Panca Inclinata", "Lat Machine"));
        athletesList.getItems().addAll(lista);
    }
}
