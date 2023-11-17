package viewone.graphical_controllers.pt;

import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
import engineering.ManageRequestList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import utils.MainStage;
import utils.SwitchPage;

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

    private final SatisfyWorkoutRequestsController satisfyWorkoutRequestsController;

    public ViewRequestGUIController() {
        satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
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
       /* try {*/
            ManageRequestList.setRequestList(requestList, satisfyWorkoutRequestsController);
            requestList.getSelectionModel().selectedItemProperty().
                    addListener(new ChangeListener<>() {
                        @Override public void changed(ObservableValue<? extends RequestBean> observableValue, RequestBean oldItem, RequestBean newItem) {
                            if(newItem != null){
                                textUsersRequest.setText(newItem.getInfo());
                                selectedRequest = newItem;
                                usernameRequestText.setText(selectedRequest.getAthleteBean().getUsername() + " Request");
                            }
                        }
                    });
/*        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }
}