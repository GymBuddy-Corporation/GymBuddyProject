package viewone.graphical_controllers.pt;

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

public class ViewRequestGUIController implements Initializable {

    @FXML
    private ListView<String> athletesList;

    @FXML
    public void goBack() throws Exception {
        String path = "/viewone/pt/PTHome.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }
    public void logout() throws Exception {
        String path = "/viewone/launcher/PTLogin.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }
    @FXML
    public void openRequest() throws Exception {
        //TODO gestisci la visualizzazione di una richiesta
        String path = "/viewone/pt/OpenSingleRequest.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> lista = new ArrayList<>(Arrays.asList("13/05/2023", "23/08/2023", "05/10/2023"));
        athletesList.getItems().addAll(lista);
    }
}