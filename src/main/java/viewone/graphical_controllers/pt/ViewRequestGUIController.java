package viewone.graphical_controllers.pt;

import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import utils.MainStage;
import utils.SwitchPage;

import java.net.URL;
/*import java.sql.SQLException;*/
import java.util.*;

public class ViewRequestGUIController implements Initializable {

    @FXML
    private ListView<String> requestList;

    @FXML
    private Text textUsersRequest;

    private RequestBean selectedRequest;

    private final SatisfyWorkoutRequestsController satisfyWorkoutRequestsController;

    public ViewRequestGUIController() {
        satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
    }

    public void setSelectedRequest(RequestBean requestBean) {
        this.selectedRequest = requestBean;
        textUsersRequest.setText(selectedRequest.getInfo());
    }

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
        SatisfyWorkoutRoutineRequestGUIController controller = (SatisfyWorkoutRoutineRequestGUIController) SwitchPage.setStage(MainStage.getStage(), "SatisfyWorkoutRoutineRequest.fxml", "pt", 1);
        Objects.requireNonNull(controller).setValue(selectedRequest, satisfyWorkoutRequestsController);
    }
    @FXML
    public void cancelRequest() throws Exception {
        //TODO gestisci la cancellazione di una richiesta
        //satisfyWorkoutRequestsController.rejectRequest(selectedRequest);
        SwitchPage.setStage(MainStage.getStage(), "PTHome.fxml", "pt", 1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> lista = new ArrayList<>(Arrays.asList("13/05/2023", "23/08/2023", "05/10/2023"));
        requestList.getItems().addAll(lista);
    }
}