package viewone.graphicalcontrollers.gym;

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

public class GymPTGUIController implements Initializable {

    @FXML
    private ListView<String> athletesList;

    @FXML
    public void goBack(){
        SwitchPage.changePage("GymHome.fxml","gym",1);
    }

    public void logout(){
        UserAccessController controller=new UserAccessController();
        controller.logout();
        SwitchPage.changePage("Login.fxml","launcher",1);
    }

    @FXML
    public void goForward()  {
        SwitchPage.changePage("GymRegistrationPT.fxml","gym",1);
    }


    @FXML
    public void addPT() {
        SwitchPage.changePage("GymRegistrationPT.fxml","gym",1);
    }

    @FXML
    public void deletePT() {
        //questo diventa il popup da sitemare e cliccabile solo se è stata scelta almeno un'opzione dalla ListView.
        //to be implemented
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> lista = new ArrayList<>(Arrays.asList("Alex", "Nazare", "Alex Nazare"));
        athletesList.getItems().addAll(lista);
    }
}
