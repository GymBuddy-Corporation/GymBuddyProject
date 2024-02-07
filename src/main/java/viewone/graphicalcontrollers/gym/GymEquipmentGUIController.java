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

public class GymEquipmentGUIController implements Initializable {

    @FXML
    private ListView<String> athletesList;

    @FXML
    public void goBack()  {
        SwitchPage.changePage("GymHome.fxml","gym",1);
    }

    @FXML
    public void goForward()  {
        SwitchPage.changePage("GymRegistrationUser.fxml","gym",1);
    }


    @FXML
    public void addEquipment() /*throws Exception*/ {
        //to be implemented
    }

    public void logout()  {
        UserAccessController controller=new UserAccessController();
        controller.logout();
        SwitchPage.changePage("Login.fxml","launcher",1);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> lista = new ArrayList<>(Arrays.asList("Panca Piana", "Panca Inclinata", "Lat Machine"));
        athletesList.getItems().addAll(lista);
    }
}
