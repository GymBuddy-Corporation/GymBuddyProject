package viewone.graphical_controllers.pt;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import utils.MainStage;
import utils.SwitchPage;
import viewone.DaysOfTheWeekButtonController;

import java.net.URL;
import java.time.DayOfWeek;
import java.util.*;

public class ManageWorkoutRoutineGUIController implements Initializable {

    @FXML private Button mondayButton;

    @FXML private TextField searchExerciseText;
    public final DaysOfTheWeekButtonController daysController = new DaysOfTheWeekButtonController();

    @FXML private ListView<String> DBExercise;
    @FXML private ListView<String> RoutineExerciselist;
    @FXML private Spinner<Integer> spinnerRepetitions;
    @FXML private Spinner<Integer> spinnerSets;
    @FXML private SplitMenuButton splitMenuRest;
    @FXML private Text labelSets;
    @FXML private Text labelRepetitions;
    @FXML private Text labelRest;
    @FXML private Button addExerciseButton;
    @FXML private Button cancelExerciseButton;

    public void setNotVisibleAdd(){
        addExerciseButton.setVisible(false);
    }
    public void setNotVisible(){
        spinnerRepetitions.setVisible(false);
        spinnerSets.setVisible(false);
        splitMenuRest.setVisible(false);
        labelSets.setVisible(false);
        labelRepetitions.setVisible(false);
        labelRest.setVisible(false);
    }
    public void setNotVisibleCancel(){
        cancelExerciseButton.setVisible(false);
    }

    public void setVisible(){
        spinnerRepetitions.setVisible(true);
        spinnerSets.setVisible(true);
        splitMenuRest.setVisible(true);
        labelSets.setVisible(true);
        labelRepetitions.setVisible(true);
        labelRest.setVisible(true);
    }
    public void setVisibleCancel() {
        cancelExerciseButton.setVisible(true);
    }
    public void setVisibleAdd() {
        addExerciseButton.setVisible(true);
    }
    @FXML
    public void logout() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"PTLogin.fxml","launcher",1);
    }
    @FXML
    public void deleteChanges() throws Exception{
        //TODO gestisci la cancellazione della scheda
        SwitchPage.setStage(MainStage.getStage(),"OpenSingleRequest.fxml","pt",1);
    }
    @FXML
    public void goHome() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }
    public void submitRoutine() throws Exception{
        //TODO gestisci il submit di una nuova scheda
        /*SwitchPage.setStage(MainStage.getStage(),"AddExercise.fxml","pt",1);*/
    }//TODO gestisci l'aggiunta di un esercizio nella scheda DB


    @FXML
    public void addExercise() {
        // Get the selected exercise from RoutineExerciselist
        String selectedExercise = DBExercise.getSelectionModel().getSelectedItem();

        // Check if an exercise is selected
        if (selectedExercise != null && !RoutineExerciselist.getItems().contains(selectedExercise)) {
            // Add the selected exercise to DBExercise
            RoutineExerciselist.getItems().add(selectedExercise);
        }
        setNotVisible();
        setNotVisibleAdd();
        DBExercise.getSelectionModel().clearSelection();
        RoutineExerciselist.getSelectionModel().clearSelection();
    }

    @FXML
    public void cancelExercise() {
        String selectedItem = RoutineExerciselist.getSelectionModel().getSelectedItem();

        // Check if an item is selected
        if (selectedItem != null) {
            // Remove the item from the underlying data model
            ObservableList<String> items = RoutineExerciselist.getItems();
            items.remove(selectedItem);

            // Optional: Clear the selection
            RoutineExerciselist.getSelectionModel().clearSelection();
        }
        setNotVisible();
        setNotVisibleCancel();
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
        DBExercise.getItems().addAll("Tricep Pushdown", "Squat", "Bicep Curl");
        setNotVisible();
        setNotVisibleAdd();
        setNotVisibleCancel();

        // Add a listener to the DBExercise ListView
        DBExercise.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // If an item is selected, show this things:
                setVisible();
                setVisibleAdd();
                setNotVisibleCancel();
                RoutineExerciselist.getSelectionModel().clearSelection();
            }
        });

        RoutineExerciselist.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setVisible();
                setVisibleCancel();
                setNotVisibleAdd();
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
