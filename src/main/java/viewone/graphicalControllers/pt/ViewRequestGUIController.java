package viewone.graphicalControllers.pt;

import beans.RequestBean;
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
    private ListView<RequestBean> requestList;

    @FXML
    private Text textUsersRequest;

    @FXML
    private Text usernameRequestText;

    private RequestBean selectedRequest;

    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }
    public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"Login.fxml","launcher",1);
    }
    @FXML
    public void askClarification() throws Exception {
        //TODO sistema la nuova grafica SwitchPage.setStage(MainStage.getStage(),"CreateNewWorkoutRoutine.fxml","pt",1);
    }

    @FXML
    public void wantToCreateNewRoutine() throws Exception {
        if (!requestList.getSelectionModel().getSelectedIndices().isEmpty()) {
            createNewRoutine();
        }
    }

    public void createNewRoutine() throws UserCastException, IOException {
        CreateNewWorkoutRoutineGUIController controller = (CreateNewWorkoutRoutineGUIController) SwitchPage.setStage(MainStage.getStage(), "CreateNewWorkoutRoutine.fxml", "pt", 1);
        Objects.requireNonNull(controller).setValue(selectedRequest);
    }

    @FXML
    public void rejectRequest() {
        //TODO gestisci la cancellazione di una richiesta
        SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
        satisfyWorkoutRequestsController.rejectRequest(selectedRequest);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
        try {
            ManageRequestList.setRequestList(requestList, satisfyWorkoutRequestsController);
            requestList.getSelectionModel().selectedItemProperty().
                    addListener(new ChangeListener<>() {
                        @Override
                        public void changed(ObservableValue<? extends RequestBean> observableValue, RequestBean oldItem, RequestBean newItem) {
                            if (newItem != null) {
                                textUsersRequest.setText(newItem.getInfo());
                                selectedRequest = newItem;
                                usernameRequestText.setText(selectedRequest.getAthleteBean().getUsername() + " Request");
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