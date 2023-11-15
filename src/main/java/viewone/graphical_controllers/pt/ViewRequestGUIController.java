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
    private ListView<String> requestList;

    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }
    public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"PTLogin.fxml","launcher",1);
    }
    @FXML
    public void askClarification() throws Exception {
        //TODO sistema la nuova grafica SwitchPage.setStage(MainStage.getStage(),"SatisfyWorkoutRoutineRequest.fxml","pt",1);
    }
    @FXML
    public void goHome() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }
    public void createNewRoutine() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"SatisfyWorkoutRoutineRequest.fxml","pt",1);
    }
    @FXML
    public void cancelRequest() throws Exception {
        //TODO gestisci la cancellazione di una richiesta
        SwitchPage.setStage(MainStage.getStage(), "PTHome.fxml", "pt", 1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> lista = new ArrayList<>(Arrays.asList("13/05/2023", "23/08/2023", "05/10/2023"));
        requestList.getItems().addAll(lista);
    }
}