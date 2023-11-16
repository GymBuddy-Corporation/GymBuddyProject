package viewone.graphical_controllers.pt;

import beans.ExerciseBean;
import beans.ExerciseForWorkoutRoutineBean;
import beans.RequestBean;
import beans.WorkoutDayBean;
import engineering.ManageExerciseList;
import engineering.list_cell_factories.ExerciseListCellFactory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import utils.ExerciseCatalogue;
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

    @FXML private ListView<ExerciseBean> DBExercise;
    @FXML private ListView<ExerciseBean> RoutineExerciselist;
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
        /*ManageExerciseList.setListener(DBExercise, daysController, satisfyWorkoutRequestsController, this);
        ManageExerciseList.setListener(RoutineExerciselist, daysController, satisfyWorkoutRequestsController, this);*/
        ExerciseCatalogue exerciseList = new ExerciseCatalogue();
        DBExercise.setCellFactory(new ExerciseListCellFactory());
        RoutineExerciselist.setCellFactory(new ExerciseListCellFactory());

        for (ExerciseBean element : exerciseList.getExerciseList()) {
            DBExercise.getItems().add(element);
        }

        mondayButton.fire();
    }



    @FXML
    public void addExercise() {
        String selectedExercise = DBExercise.getSelectionModel().getSelectedItem().getName();
        String selectedDay = daysController.getDay();

        setVisibleLabel(false);
        setVisibleAdd(false);
        DBExercise.getSelectionModel().clearSelection();
        RoutineExerciselist.getSelectionModel().clearSelection();

        int repetitions = spinnerRepetitions.getValue();
        int sets = spinnerSets.getValue();
        String rest = restTimeComboBox.getValue();
        boolean test = true;
        if (selectedExercise != null && !RoutineExerciselist.getItems().contains(selectedExercise)
                && sets!=0 && repetitions !=0) {
            ExerciseForWorkoutRoutineBean newExercise = new ExerciseForWorkoutRoutineBean(selectedDay, selectedExercise);
            newExercise.setRepetitions(repetitions);
            newExercise.setSets(sets);
            if(!newExercise.setRest(rest)){
                test = false;
            }
            if(test){
                RoutineExerciselist.getItems().add(newExercise);
                System.out.println("Hai appena aggiunto l'esercizio " + selectedExercise + " nel giorno: " + selectedDay+
                        " con "+ repetitions + " ripetizioni, "+ sets + " set e " + rest+ " minuti di recupero.\n\n");
            } else {
                System.out.println("Esercizio non inserito\n");
            }

        }

        //satisfyWorkoutRequestsController.addExerciseToWorkoutDay(selectedExercise, selectedDay, RoutineExerciselist);
    }

    @FXML
    public void cancelExercise() {
        String selectedItem = RoutineExerciselist.getSelectionModel().getSelectedItem().getName();
        if (selectedItem != null) {
            ObservableList<ExerciseBean> items = RoutineExerciselist.getItems();
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

    public void resetInput(){
        restTimeComboBox.setValue("00:00");
        spinnerRepetitions.getValueFactory().setValue(0);
        spinnerSets.getValueFactory().setValue(0);
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
                resetInput();
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
