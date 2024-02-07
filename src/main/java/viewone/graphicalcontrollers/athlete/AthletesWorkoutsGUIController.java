package viewone.graphicalcontrollers.athlete;

import controllers.UserAccessController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import utils.SwitchPage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AthletesWorkoutsGUIController implements Initializable {

    @FXML
    private ListView<String> athletesList;

    @FXML
    public void goBack()  {
        SwitchPage.changePage("AthleteHome.fxml","athlete",1);
    }
    public void logout() {
        UserAccessController controller=new UserAccessController();
        controller.logout();
        SwitchPage.changePage("Login.fxml","launcher",1);
    }
    @FXML
    public void addWorkout() {
        //gestisci la rischiesta di una nuova scheda, to be implemented
    }

    @FXML
    public void modifyWorkout() {
        //gestisci la modifica di una scheda,to be implemented
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> lista = new ArrayList<>(Arrays.asList("13/05/2023", "23/08/2023", "05/10/2023"));
        athletesList.getItems().addAll(lista);
    }
}
