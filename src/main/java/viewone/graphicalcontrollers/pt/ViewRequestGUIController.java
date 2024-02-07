package viewone.graphicalcontrollers.pt;

import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
import controllers.UserAccessController;
import exceptions.CostumException;
import exceptions.DBUnrreachableException;
import exceptions.NoLoggedUserException;
import exceptions.dataexception.DataFieldException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import utils.SwitchPage;
import viewone.managelistview.ManageRequestList;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ViewRequestGUIController implements Initializable {

    @FXML
    private ListView<RequestBean> requestList;

    @FXML
    private Text textUsersRequest;
    @FXML private Text usernameText;

    @FXML
    private Text usernameRequestText;

    private RequestBean selectedRequest;

    @FXML
    public void goBack()  {
        SwitchPage.changePage("PTHome.fxml","pt",1);
    }
    @FXML public void logout() {

            UserAccessController controller = new UserAccessController();
            controller.logout();
            SwitchPage.changePage( "Login.fxml", "launcher", 1);

    }
    @FXML
    public void askClarification() {
        if (!requestList.getSelectionModel().getSelectedIndices().isEmpty()) {
            EmailSystemGUIController controller = (EmailSystemGUIController) SwitchPage.changePage("EmailSystem.fxml","pt",1);
            Objects.requireNonNull(controller).setValue(selectedRequest);
        }
    }

    @FXML
    public void wantToCreateNewRoutine()  {
        if (!requestList.getSelectionModel().getSelectedIndices().isEmpty()) {
            createNewRoutine();
        }
    }

    public void createNewRoutine() {
        CreateNewWorkoutRoutineGUIController controller = (CreateNewWorkoutRoutineGUIController) SwitchPage.changePage( "CreateNewWorkoutRoutine.fxml", "pt", 1);
        Objects.requireNonNull(controller).setValue(selectedRequest);
    }

    @FXML
    public void rejectRequest() {
        SatisfyWorkoutRequestsController controller;
        try {
            controller = new SatisfyWorkoutRequestsController();
        } catch (NoLoggedUserException e) {
            e.callMe(2);
            return;
        }
        if (requestList.getSelectionModel().getSelectedIndices().isEmpty()) {
            return;
        }
        controller.rejectRequest(selectedRequest);
        try {
            ManageRequestList.updateList(requestList, controller);
        } catch (DBUnrreachableException | DataFieldException e) {
            e.callMe(2);
            return;
        }
        textUsersRequest.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameText.setText(new UserAccessController().getUser().getUsername());
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
        }
    }
}