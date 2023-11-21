package viewone.graphical_controllers.pt;

import beans.ExerciseBean;
import beans.ExerciseForWorkoutRoutineBean;
import beans.RequestBean;
import engineering.ExerciseForWOListCellFactory;
import engineering.ExerciseListCellFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import utils.ExerciseCatalogue;
import engineering.ManageExerciseList;
import utils.MainStage;
import utils.SwitchPage;
import viewone.DaysOfTheWeekButtonController;
import controllers.SatisfyWorkoutRequestsController;

import java.net.URL;
import java.util.*;

public class SatisfyWorkoutRoutineRequestGUIController implements Initializable {

    @FXML private Button mondayButton;
    //@FXML private TextField searchExerciseText;

    private final Map<String, List<ExerciseForWorkoutRoutineBean>> dayExercisesMap = new HashMap<>();
    public final DaysOfTheWeekButtonController daysController = new DaysOfTheWeekButtonController();

    private RequestBean requestBean;
    private String selectedDay;

    @FXML private ListView<ExerciseBean> DBExercise;
    @FXML private ListView<ExerciseForWorkoutRoutineBean> RoutineExerciselist;
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

    public void resetSelection(int choice){
        if(choice == 1){
            RoutineExerciselist.getSelectionModel().clearSelection();
        } else {
            DBExercise.getSelectionModel().clearSelection();
        }
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
    @FXML
    public void submitRoutine() throws Exception{
        //TODO gestisci il submit di una nuova scheda, con l'aggiunta di un eventuale commento.
        satisfyWorkoutRequestsController.submitRoutine(/*this.RoutineExerciselist*/);
        AddCommentToWorkoutRoutineGUIController controller = (AddCommentToWorkoutRoutineGUIController) SwitchPage.setStage(MainStage.getStage(),"AddCommentToWorkoutRoutine.fxml","pt",1);
        Objects.requireNonNull(controller).setValue(requestBean, satisfyWorkoutRequestsController);

    }//TODO gestisci l'aggiunta di un esercizio nella scheda DB


    public void setValue(RequestBean request, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) {
        requestBean = request;
        this.satisfyWorkoutRequestsController = satisfyWorkoutRequestsController;
        ManageExerciseList.setListenerDB(DBExercise, daysController, satisfyWorkoutRequestsController, this);
        ManageExerciseList.setListenerRoutineWorkout(RoutineExerciselist, daysController, satisfyWorkoutRequestsController, this);
        mondayButton.fire();
    }


    public boolean checkAlreadyAdded(ExerciseForWorkoutRoutineBean exerciseForWorkoutRoutineBean) {
        /*WorkoutDayBean workoutDayBean = satisfyWorkoutRequestsController.getWorkoutDayBean(new DayBeanA(exerciseForWorkoutRoutineBean.getDay()));*/
        for (ExerciseForWorkoutRoutineBean exercise : RoutineExerciselist.getItems()/*workoutDayBean.getExerciseBeanList()*/) {
            /*Il commento serve perchè poi scorreremo tutto il giorno e vedremo se c'è SOLO per quel giorno*/
            if (Objects.equals(exercise.getExercise(), exerciseForWorkoutRoutineBean.getExercise())) {
                System.out.println("Esercizio già inserito nella tua scheda\n");
                return true;
            }
        }
        return false;
    }

    public void setExerciseDetails(int repetitions, int sets, String rest){
        repetLabelExerciseInserted.setText(Integer.toString(repetitions));
        setLabelExerciseInserted.setText(Integer.toString(sets));
        restLabelExerciseInserted.setText(rest);
    }



    @FXML
    public void addExercise() {
        ExerciseBean selectedExercise = DBExercise.getSelectionModel().getSelectedItem();

        if (selectedExercise != null && selectedDay != null) {
            String exerciseName = selectedExercise.getName();

            setVisibleLabel(false);
            setVisibleAdd(false);

            int repetitions = spinnerRepetitions.getValue();
            int sets = spinnerSets.getValue();
            String rest = restTimeComboBox.getValue();
            boolean test = true;

            ExerciseForWorkoutRoutineBean newExercise = new ExerciseForWorkoutRoutineBean(selectedDay, selectedExercise);

            if (!checkAlreadyAdded(newExercise) && sets != 0 && repetitions != 0) {
                newExercise.setRepetitions(repetitions);
                newExercise.setSets(sets);

                if (!newExercise.setRest(rest)) {
                    test = false;
                }

                if (test) {
                    satisfyWorkoutRequestsController.addExerciseToWorkoutDay(newExercise, repetitions, sets, rest, RoutineExerciselist);
                    // Update the map with the new exercise
                    dayExercisesMap.get(selectedDay).add(newExercise);
                } else {
                    System.out.println("Esercizio non inserito\n");
                }
            }
            resetSelection(1);
            DBExercise.getSelectionModel().clearSelection();
            RoutineExerciselist.getSelectionModel().clearSelection();
        }
    }


    @FXML
    public void cancelExercise() {
        ExerciseBean selectedExercise = RoutineExerciselist.getSelectionModel().getSelectedItem().getExercise();
        if (selectedExercise != null) {
            for (ExerciseForWorkoutRoutineBean  item : RoutineExerciselist.getItems()) {
                if (selectedExercise.equals(item.getExercise())) {
                    RoutineExerciselist.getItems().remove(item);
                    break; // Exit the loop once the item is removed
                }
            }
        }
        setVisibleLabel(false);
        setVisibleCancel(false);
        resetSelection(2);
        DBExercise.getSelectionModel().clearSelection();
        RoutineExerciselist.getSelectionModel().clearSelection();
    }

    @FXML
    public void searchExercise(){
        //TODO gestisci la ricerca di un esercizio dal database
    }

    @FXML
    void dayButtonAction(ActionEvent event) {
        selectedDay = daysController.dayButtonAction(event);

        // If exercises for the selected day are not loaded, load them
        if (!dayExercisesMap.containsKey(selectedDay)) {
            dayExercisesMap.put(selectedDay, new ArrayList<>());
        }

        // Update the RoutineExerciselist with exercises for the selected day
        RoutineExerciselist.getItems().setAll(dayExercisesMap.get(selectedDay));

        // Optionally, update other UI elements based on the selected day
    }


    public void resetInputCollectors(){
        restTimeComboBox.setValue("00:00");
        spinnerRepetitions.getValueFactory().setValue(0);
        spinnerSets.getValueFactory().setValue(0);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setVisibleAdd(false);
        setVisibleLabel(false);
        setVisibleCancel(false);
        DBExercise.setCellFactory(new ExerciseListCellFactory());
        RoutineExerciselist.setCellFactory(new ExerciseForWOListCellFactory());
        ExerciseCatalogue exerciseList = new ExerciseCatalogue();

        for (ExerciseBean element : exerciseList.getExerciseList()) {
            DBExercise.getItems().add(element);
        }
    }
}