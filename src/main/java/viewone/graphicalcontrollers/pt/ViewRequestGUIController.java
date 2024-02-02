package viewone.graphicalcontrollers.pt;

import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
import exceptions.CostumException;
import viewone.managelistview.ManageRequestList;
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
    @FXML public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"Login.fxml","launcher",1);
    }
    @FXML
    public void askClarification() throws IOException{
        if (!requestList.getSelectionModel().getSelectedIndices().isEmpty()) {
            EmailSystemGUIController controller = (EmailSystemGUIController) SwitchPage.setStage(MainStage.getStage(),"EmailSystem.fxml","pt",1);
            Objects.requireNonNull(controller).setValue(selectedRequest);
        }
    }

    @FXML
    public void wantToCreateNewRoutine() throws Exception {
        if (!requestList.getSelectionModel().getSelectedIndices().isEmpty()) {
            createNewRoutine();
        }
    }

    public void createNewRoutine() throws IOException {
        CreateNewWorkoutRoutineGUIController controller = (CreateNewWorkoutRoutineGUIController) SwitchPage.setStage(MainStage.getStage(), "CreateNewWorkoutRoutine.fxml", "pt", 1);
        Objects.requireNonNull(controller).setValue(selectedRequest);
    }

    @FXML
    public void rejectRequest() throws IOException {
        //TODO gestisci la cancellazione di una richiesta

        try {
            SatisfyWorkoutRequestsController controller = new SatisfyWorkoutRequestsController();
            controller.rejectRequest(selectedRequest);
            if (requestList.getSelectionModel().getSelectedIndices().isEmpty()) {
                return;
            }
            ManageRequestList.updateList(requestList, controller);
            textUsersRequest.setText("");
        }catch (CostumException e){
            e.callMe(1);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            SatisfyWorkoutRequestsController controller = new SatisfyWorkoutRequestsController();
            ManageRequestList.setRequestList(requestList, controller);
            requestList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends RequestBean> observableValue, RequestBean oldItem, RequestBean newItem) {
                    if (newItem != null) {
                        textUsersRequest.setText(newItem.getInfo());
                        selectedRequest = newItem;
                        usernameRequestText.setText(selectedRequest.getAthleteBean().getUsername() + " Request");
                    }
                }
            });
        }catch (CostumException e){
                e.callMe(1);
                throw new RuntimeException(e);
        }
    }
}