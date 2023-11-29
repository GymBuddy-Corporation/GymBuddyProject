package viewone.graphicalControllers.pt;

import beans.ExerciseBean;
import beans.ExerciseForWorkoutRoutineBean;
import beans.RequestBean;
import beans.SearchBean;
import engineering.manageListView.listCells.ExerciseForWOListCellFactory;
import engineering.manageListView.listCells.ExerciseListCellFactory;
import exceptions.InvalidExerciseFeaturesException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import engineering.manageListView.ManageExerciseList;
import utils.MainStage;
import utils.SwitchPage;
import viewone.DaysOfTheWeekButtonController;
import controllers.SatisfyWorkoutRequestsController;

import java.net.URL;
import java.util.*;

public class SatisfyWorkoutRoutineRequestGUIController implements Initializable {

    @FXML private Button mondayButton;
    //@FXML private TextField searchExerciseText;

    private Map<String, List<ExerciseForWorkoutRoutineBean>> dayExercisesMap = new HashMap<>();
    public final DaysOfTheWeekButtonController daysController = new DaysOfTheWeekButtonController();

    private RequestBean requestBean;
    private String selectedDay;

    @FXML private ListView<ExerciseBean> exerciseDBList;
    @FXML private ListView<ExerciseForWorkoutRoutineBean> routineExerciselist;
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
    @FXML
    private TextField searchExerciseText;

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
            routineExerciselist.getSelectionModel().clearSelection();
        } else {
            exerciseDBList.getSelectionModel().clearSelection();
        }
    }
    @FXML
    public void logout() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"Login.fxml","launcher",1);
    }
    @FXML
    public void setExerciseStatus() throws Exception{
        SetExerciseStatusGUIController controller = (SetExerciseStatusGUIController) SwitchPage.setStage(MainStage.getStage(),"SetExerciseStatus.fxml","pt",1);
        Objects.requireNonNull(controller).setValue(requestBean, satisfyWorkoutRequestsController, dayExercisesMap);
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
        satisfyWorkoutRequestsController.submitRoutine(requestBean);
        AddCommentToWorkoutRoutineGUIController controller = (AddCommentToWorkoutRoutineGUIController) SwitchPage.setStage(MainStage.getStage(),"AddCommentToWorkoutRoutine.fxml","pt",1);
        Objects.requireNonNull(controller).setValue(requestBean, satisfyWorkoutRequestsController);

    }//TODO gestisci l'aggiunta di un esercizio nella scheda DB


    public void setValue(RequestBean request, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) {
        this.requestBean = request;
        this.satisfyWorkoutRequestsController = satisfyWorkoutRequestsController;
        ManageExerciseList.setListenerDB(exerciseDBList, satisfyWorkoutRequestsController, this);
        ManageExerciseList.setListenerRoutineWorkout(routineExerciselist, satisfyWorkoutRequestsController, this);
        List<ExerciseBean> exerciseBeanList = satisfyWorkoutRequestsController.getGymExerciseBean();
        ManageExerciseList.updateListFiltered(exerciseDBList, exerciseBeanList);
        mondayButton.fire();
        System.out.println("Username:" + this.requestBean.getAthleteBean().getUsernameBean() +
                "data" + this.requestBean.getRequestDate() + "trainer" + this.requestBean.getTrainerFc());
    }

    //Adjust this method, I think it's necessary to pass back all the information
    //if I come back after setting N exercises' status.

    public void setBackValue(RequestBean request, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, Map<String, List<ExerciseForWorkoutRoutineBean>> dayExercisesMap) {
        this.requestBean = request;
        this.dayExercisesMap=dayExercisesMap;
        this.satisfyWorkoutRequestsController = satisfyWorkoutRequestsController;
        ManageExerciseList.setListenerDB(exerciseDBList, satisfyWorkoutRequestsController, this);
        ManageExerciseList.setListenerRoutineWorkout(routineExerciselist, satisfyWorkoutRequestsController, this);
        List<ExerciseBean> exerciseBeanList = satisfyWorkoutRequestsController.getGymExerciseBean();
        ManageExerciseList.updateListFiltered(exerciseDBList, exerciseBeanList);
        mondayButton.fire();
    }

    public boolean checkAlreadyAdded(ExerciseForWorkoutRoutineBean exerciseForWorkoutRoutineBean) {
        /*WorkoutDayBean workoutDayBean = satisfyWorkoutRequestsController.getWorkoutDayBean(new DayBeanA(exerciseForWorkoutRoutineBean.getDay()));*/
        for (ExerciseForWorkoutRoutineBean exercise : routineExerciselist.getItems()/*workoutDayBean.getExerciseBeanList()*/) {
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
    public void addExercise() throws InvalidExerciseFeaturesException {
        ExerciseBean selectedExercise = exerciseDBList.getSelectionModel().getSelectedItem();

        if (selectedExercise != null && selectedDay != null) {

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
                    satisfyWorkoutRequestsController.addExerciseToWorkoutDay(newExercise, routineExerciselist);
                    // Update the map with the new exercise
                    dayExercisesMap.computeIfAbsent(newExercise.getDay(), k -> new ArrayList<>()).add(newExercise);
                } else {
                    System.out.println("Esercizio non inserito\n");
                }
            }
            resetSelection(1);
            exerciseDBList.getSelectionModel().clearSelection();
            routineExerciselist.getSelectionModel().clearSelection();
        }
    }

    @FXML
    public void cancelExercise() {
        ExerciseForWorkoutRoutineBean selectedExercise = routineExerciselist.getSelectionModel().getSelectedItem();
        if (selectedExercise != null) {
            satisfyWorkoutRequestsController.removeExerciseToWorkoutDay(selectedExercise.getExercise(), routineExerciselist, dayExercisesMap);

            // Remove the exercise from the dayExercisesMap
            List<ExerciseForWorkoutRoutineBean> dayExercises = dayExercisesMap.get(selectedExercise.getDay());
            if (dayExercises != null) {
                dayExercises.removeIf(dayExercise -> dayExercise.getExercise().getName().equals(selectedExercise.getExercise().getName()));
            }
        }
        setVisibleLabel(false);
        setVisibleCancel(false);
        resetSelection(2);
        exerciseDBList.getSelectionModel().clearSelection();
        routineExerciselist.getSelectionModel().clearSelection();
    }

    @FXML
    void dayButtonAction(ActionEvent event) {
        selectedDay = daysController.dayButtonAction(event);

        // If exercises for the selected day are not loaded, load them
        if (!dayExercisesMap.containsKey(selectedDay)) {
            dayExercisesMap.put(selectedDay, new ArrayList<>());
        }

        // Update the RoutineExerciselist with exercises for the selected day
        routineExerciselist.getItems().setAll(dayExercisesMap.get(selectedDay));

        // Optionally, update other UI elements based on the selected day
    }

    public void resetInputCollectors(){
        restTimeComboBox.setValue("00:00");
        spinnerRepetitions.getValueFactory().setValue(0);
        spinnerSets.getValueFactory().setValue(0);
    }

    @FXML public void searchButtonAction() {
        List<ExerciseBean> exerciseBeanList = satisfyWorkoutRequestsController.searchExercise(new SearchBean(searchExerciseText.getText()));
        ManageExerciseList.updateListFiltered(exerciseDBList, exerciseBeanList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setVisibleAdd(false);
        setVisibleLabel(false);
        setVisibleCancel(false);
        exerciseDBList.setCellFactory(new ExerciseListCellFactory());
        routineExerciselist.setCellFactory(new ExerciseForWOListCellFactory());

    }
}