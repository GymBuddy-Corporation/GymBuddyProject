package viewone.graphicalControllers.pt;

import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
import engineering.manageListView.ManageRequestList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import model.Exercise;
import model.ExerciseStatus;
import model.Gym;
import model.Trainer;
import model.record.Credentials;
import model.record.PersonalInfo;
import utils.MainStage;
import utils.SwitchPage;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class ViewRequestGUIController implements Initializable {

    @FXML
    private ListView<RequestBean> requestList;

    @FXML
    private Text textUsersRequest;

    @FXML
    private Text usernameRequestText;

    private RequestBean selectedRequest;
    private Trainer trainer; //poi gestisci con loggedUser (vedi todo)

    private final SatisfyWorkoutRequestsController satisfyWorkoutRequestsController;

    public ViewRequestGUIController() {
        List<Exercise>exerciseList = new ArrayList<>();


        //TODO ATTENZIONE RICORDA LA VIEW NON COMUNICA CON I MODEL
        // SISTEMA BENE
        Gym gym1 = new Gym("Palestra1",
                new Credentials("gym1@gmail.com", "forzanapule1926"),
                "IBAN1112223334444", "Napoli", "Via largo Maradroga, 71");
        /*Gym gym2 = new Gym("Palestra2");
        Gym gym3 = new Gym("Palestra3");,
        Gym gym4 = new Gym("Palestra4");*/

        Exercise ex1 = new Exercise("Tricep Pushdown", gym1);
        Exercise ex2 = new Exercise("Shoulder Press", gym1);
        Exercise ex3 = new Exercise("Squat", gym1);
        Exercise ex4 = new Exercise("Dips", gym1);

        ex3.setStatus(ExerciseStatus.SUSPENDED);
        ex4.setStatus(ExerciseStatus.SUSPENDED);

        exerciseList.add(ex1);
        exerciseList.add(ex2);
        exerciseList.add(ex3);
        exerciseList.add(ex4);
        this.trainer = new Trainer("AleCortix",
                new PersonalInfo("Alessandro", "Cortese", LocalDate.now(), "CRTLSN99T24H501R", 'm'),
                new Credentials("alecortix@gmail.com", "forzanapule1926"));
        satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController(trainer, exerciseList);
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
                            usernameRequestText.setText(selectedRequest.getAthleteBean().getUsernameBean() + " Request");
                        }
                    }
                });

/*        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }
}