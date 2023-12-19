package viewone.graphicalControllers.pt;

import viewone.RequestBean1;
import controllers.SatisfyWorkoutRequestsController;
import engineering.manageListView.ManageRequestList;
import exceptions.UserCastException;
import exceptions.dataException.DataFieldException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import utils.MainStage;
import utils.SwitchPage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ViewRequestGUIController implements Initializable {

    @FXML
    private ListView<RequestBean1> requestList;

    @FXML
    private Text textUsersRequest;

    @FXML
    private Text usernameRequestText;

    private RequestBean1 selectedRequest;

    private final SatisfyWorkoutRequestsController satisfyWorkoutRequestsController;

    public ViewRequestGUIController() {
        satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
    }

    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }
    public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"Login.fxml","launcher",1);
    }
    @FXML
    public void askClarification() throws Exception {
        //TODO sistema la nuova grafica SwitchPage.setStage(MainStage.getStage(),"SatisfyWorkoutRoutineRequest.fxml","pt",1);
    }

    @FXML
    public void wantToCreateNewRoutine() throws Exception {
        if (!requestList.getSelectionModel().getSelectedIndices().isEmpty()) {
            createNewRoutine();
        }
    }

    public void createNewRoutine() throws UserCastException, IOException {
        SatisfyWorkoutRoutineRequestGUIController controller = (SatisfyWorkoutRoutineRequestGUIController) SwitchPage.setStage(MainStage.getStage(), "SatisfyWorkoutRoutineRequest.fxml", "pt", 1);
        Objects.requireNonNull(controller).setValue(selectedRequest);
    }

    @FXML
    public void rejectRequest() {
        //TODO gestisci la cancellazione di una richiesta
        satisfyWorkoutRequestsController.rejectRequest(selectedRequest);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ManageRequestList.setRequestList(requestList, satisfyWorkoutRequestsController);
            requestList.getSelectionModel().selectedItemProperty().
                    addListener(new ChangeListener<>() {
                        @Override
                        public void changed(ObservableValue<? extends RequestBean1> observableValue, RequestBean1 oldItem, RequestBean1 newItem) {
                            if (newItem != null) {
                                textUsersRequest.setText(newItem.getInfo());
                                selectedRequest = newItem;
                                usernameRequestText.setText(selectedRequest.getAthleteBean().getUsername() + " Request");
                                /*System.out.println("Username:" + selectedRequest.getAthleteBean().getUsername() +
                                        "data" + selectedRequest.getRequestDate() + "trainer" + selectedRequest.getTrainerFc());*/
                            }
                        }
                    });
        }catch (DataFieldException e) {
            try {
                e.callMe(1);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}