package viewone.graphical_controllers.pt;

import beans.ExerciseBean;
import beans.RequestBean;
import beans.WorkoutDayBean;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import utils.MainStage;
import utils.SwitchPage;
import viewone.DaysOfTheWeekButtonController;
import controllers.SatisfyWorkoutRequestsController;

import java.net.URL;
import java.util.*;

public class SatisfyWorkoutRoutineRequestGUIController implements Initializable {

    @FXML private Button mondayButton;
    @FXML private TextField searchExerciseText;
    public final DaysOfTheWeekButtonController daysController = new DaysOfTheWeekButtonController();

    private RequestBean requestBean;

    @FXML private ListView<String> DBExercise;
    @FXML private ListView<String> RoutineExerciselist;
    @FXML private Spinner<Integer> spinnerRepetitions;
    @FXML private Spinner<Integer> spinnerSets;
    @FXML private ComboBox<String> restTimeComboBox;
    @FXML private Text labelSets;
    @FXML private Text labelRepetitions;
    @FXML private Text labelRest;
    @FXML private Button addExerciseButton;
    @FXML private Button cancelExerciseButton;
    @FXML private Label repetLabelExerciseInserted;
    @FXML private Label setLabelExerciseInserted;
    @FXML private Label restLabelExerciseInserted;


    private SatisfyWorkoutRequestsController satisfyWorkoutRequestsController;

    public void setVisibleAdd(Boolean bool){
        spinnerRepetitions.setVisible(bool);
        spinnerSets.setVisible(bool);
        restTimeComboBox.setVisible(bool);
        addExerciseButton.setVisible(bool);
    }
    public void setVisibleCancel(Boolean bool) {
        cancelExerciseButton.setVisible(bool);
        repetLabelExerciseInserted.setVisible(bool);
        setLabelExerciseInserted.setVisible(bool);
        restLabelExerciseInserted.setVisible(bool);
    }
    public void setVisibleLabel(Boolean bool) {
        labelSets.setVisible(bool);
        labelRepetitions.setVisible(bool);
        labelRest.setVisible(bool);
    }
    @FXML
    public void logout() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"PTLogin.fxml","launcher",1);
    }
    @FXML
    public void deleteChanges() throws Exception{
        //TODO gestisci la cancellazione della scheda
        SwitchPage.setStage(MainStage.getStage(),"ViewWorkoutRoutineRequests.fxml","pt",1);
    }
    @FXML
    public void goHome() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }
    public void submitRoutine() throws Exception{
        //TODO gestisci il submit di una nuova scheda, con l'aggiunta di un eventuale commento.
        /*SwitchPage.setStage(MainStage.getStage(),"AddExercise.fxml","pt",1);*/
    }//TODO gestisci l'aggiunta di un esercizio nella scheda DB


    public void setValue(RequestBean request, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) {
        requestBean = request;
        this.satisfyWorkoutRequestsController = satisfyWorkoutRequestsController;
       /* ExerciseBean ex1 = new ExerciseBean("Tricep Pushdown");
        ExerciseBean ex2 = new ExerciseBean("Shoulder Press");
        ExerciseBean ex3 = new ExerciseBean("Squat");
        ExerciseBean ex4 = new ExerciseBean("Dips");
        List<ExerciseBean> exerciseBeanList = new ArrayList<>(Arrays.asList(ex1, ex2, ex3, ex4));
*/
        DBExercise.getItems().addAll("Tricep Pushdown", "Squat", "Bicep Curl");
        mondayButton.fire();
    }

    @FXML
    public void addExercise() {
        String selectedExercise = DBExercise.getSelectionModel().getSelectedItem();
        String selectedDay = daysController.getDay();
        satisfyWorkoutRequestsController.addExerciseToWorkoutDay(selectedExercise, selectedDay, RoutineExerciselist);

        if (selectedExercise != null && !RoutineExerciselist.getItems().contains(selectedExercise)) {
            RoutineExerciselist.getItems().add(selectedExercise);
        }
        setVisibleLabel(false);
        setVisibleAdd(false);
        DBExercise.getSelectionModel().clearSelection();
        RoutineExerciselist.getSelectionModel().clearSelection();
    }

    @FXML
    public void cancelExercise() {
        String selectedItem = RoutineExerciselist.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            ObservableList<String> items = RoutineExerciselist.getItems();
            items.remove(selectedItem);
            RoutineExerciselist.getSelectionModel().clearSelection();
        }
        setVisibleLabel(false);
        setVisibleCancel(false);
    }

    @FXML
    public void searchExercise(){
        //TODO gestisci la ricerca di un esercizio dal database
    }

    @FXML void dayButtonAction(ActionEvent event) {
        daysController.dayButtonAction(event);
        //updateSelectedExerciseList();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mondayButton.fire();

        setVisibleAdd(false);
        setVisibleLabel(false);
        setVisibleCancel(false);
        DBExercise.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setVisibleLabel(true);
                setVisibleAdd(true);
                setVisibleCancel(false);
                RoutineExerciselist.getSelectionModel().clearSelection();
            }
        });

        RoutineExerciselist.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setVisibleLabel(true);
                setVisibleCancel(true);
                setVisibleAdd(false);
                DBExercise.getSelectionModel().clearSelection();
            }
        });
        /*
        TODO sistema poi le liste con funzioni del genere
        exerciseList.setCellFactory(nodeListView -> new ExerciseListCellFactory(false));
        selectedExerciseList.setCellFactory(nodeListView -> new ExerciseListCellFactory(false));
        */


    }
}
