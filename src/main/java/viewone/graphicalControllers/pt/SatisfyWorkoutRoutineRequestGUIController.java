package viewone.graphicalControllers.pt;

import beans.*;
import engineering.LoggedUserSingleton;
import engineering.Observer;
import engineering.manageListView.listCells.ExerciseForWOListCellFactory;
import engineering.manageListView.listCells.ExerciseListCellFactory;
import exceptions.UserCastException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import engineering.manageListView.ManageExerciseList;
import model.Exercise;
import utils.MainStage;
import utils.SwitchPage;
import viewone.DaysOfTheWeekButtonController;
import controllers.SatisfyWorkoutRequestsController;
import viewone.RequestBean1;
import viewone.WorkoutRoutineBean1;

import java.net.URL;
import java.util.*;

public class SatisfyWorkoutRoutineRequestGUIController implements Initializable, Observer {

    @FXML private Button mondayButton;
    public final DaysOfTheWeekButtonController daysController = new DaysOfTheWeekButtonController();
    private RequestBean1 requestBean;
    private String selectedDay;
    private final WorkoutRoutineBean1 workoutRoutine = new WorkoutRoutineBean1(); //TODO replace hash map with this

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
    @FXML private Text athletesNameRoutineText;
    @FXML private TextField searchExerciseText;

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
        SwitchPage.saveElement("SatisfyWorkoutRoutineRequest.fxml","pt",labelRest.getScene(),this);
        SetExerciseStatusGUIController controller = (SetExerciseStatusGUIController) SwitchPage.setStage(MainStage.getStage(),"SetExerciseStatus.fxml","pt",1);
        Objects.requireNonNull(controller).setValue(this);
    }
    @FXML
    public void deleteChanges() throws Exception{
        SwitchPage.deleteElement("SatisfyWorkoutRoutineRequest.fxml","pt");

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
        // gestisci l'aggiunta di un esercizio nella scheda DB
        AddCommentToWorkoutRoutineGUIController controller = (AddCommentToWorkoutRoutineGUIController) SwitchPage.setStage(MainStage.getStage(),"AddCommentToWorkoutRoutine.fxml","pt",1);
        Objects.requireNonNull(controller).setValue(requestBean, this.workoutRoutine);

    }

    public void setValue(RequestBean1 request){
        this.requestBean = request;
        List<ExerciseBean> exerciseBeanList;
        SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
        exerciseBeanList = satisfyWorkoutRequestsController.getGymExerciseBean();
        for (Exercise ex : LoggedUserSingleton.getSingleton().getExcerciseInventory().getExerciseList()) {
            ex.addObserver(this);
        }
        ManageExerciseList.setListenerDB(exerciseDBList, satisfyWorkoutRequestsController, this);
        ManageExerciseList.setListenerRoutineWorkout(routineExerciselist, satisfyWorkoutRequestsController, this);

        ManageExerciseList.updateListFiltered(exerciseDBList, exerciseBeanList);
        ManageExerciseList.updateListFilteredDB(routineExerciselist, exerciseBeanList);
        mondayButton.fire();
        athletesNameRoutineText.setText(requestBean.getAthleteBean().getUsername() + "s' Routine");

    }

    public void setExerciseDetails(int repetitions, int sets, String rest){
        repetLabelExerciseInserted.setText(Integer.toString(repetitions));
        setLabelExerciseInserted.setText(Integer.toString(sets));
        restLabelExerciseInserted.setText(rest);
    }

    @FXML
    public void addExercise() {
        final ExerciseBean selectedExercise = exerciseDBList.getSelectionModel().getSelectedItem();
        if (selectedExercise != null && selectedDay != null) {
            setVisibleLabel(false);
            setVisibleAdd(false);

            int repetitions = spinnerRepetitions.getValue();
            int sets = spinnerSets.getValue();
            String rest = restTimeComboBox.getValue();
            boolean toAdd = true;

            ExerciseForWorkoutRoutineBean newExercise =
                    new ExerciseForWorkoutRoutineBean(selectedExercise.getName(),
                            selectedExercise.getStatusExercise(),
                            selectedDay);
            newExercise.setRepetitions(repetitions);
            newExercise.setSets(sets);
            if (!newExercise.setRest(rest)) {
                toAdd = false;
                //TODO gestisci sta cosa
            } if (toAdd) {
                if (newExercise.getSets() == 0 || newExercise.getRepetitions() == 0) {
                    System.out.println("Sets and repetitions must be greater than 0.");
                    return;
                }

                WorkoutDayBean workoutDay = workoutRoutine.getWorkoutDay(newExercise.getDay());

                // If the workout day doesn't exist, create it
                if (workoutDay == null) {
                    workoutDay = new WorkoutDayBean(newExercise.getDay());
                    workoutRoutine.addWorkoutDayBean(workoutDay);
                }

                // Check if the exercise is already added to the workout day
                for (ExerciseForWorkoutRoutineBean existingExercise : workoutDay.getExerciseList()) {
                    if (existingExercise.getName().equals(newExercise.getName())) {
                        System.out.println("Exercise '" + newExercise.getName() + "' is already added to the workout day.");
                        return; // Don't add the exercise if it's already in the workout day
                    }
                }

                // Add the exercise to the workout day
                workoutDay.addExerciseBean(newExercise);
                List<ExerciseForWorkoutRoutineBean> activeExercises = new ArrayList<>();
                for (ExerciseForWorkoutRoutineBean exercise : workoutRoutine.getWorkoutDay(newExercise.getDay()).getExerciseBeanList()) {
                    if (exercise.getStatusExercise() == ExerciseStatusBean.ACTIVE) {
                        activeExercises.add(exercise);
                    }
                }

                // Set the filtered list in the ListView
                routineExerciselist.getItems().setAll(activeExercises);
                } else {
                //TODO gestisci il non inserimento
                System.out.println("Exercise was not added successfully.");
            }
            resetSelection(1);
            exerciseDBList.getSelectionModel().clearSelection();
            routineExerciselist.getSelectionModel().clearSelection();
        }
    }

    public void removeExerciseFromDWorkoutRoutineBean (ExerciseForWorkoutRoutineBean exercise) {
        // Iterate through the map and remove the exercise for the corresponding day
        workoutRoutine.getWorkoutDay(exercise.getDay()).removeExerciseBean(exercise);
    }

    @FXML
    public void cancelExercise() {
        ExerciseForWorkoutRoutineBean selectedExercise = routineExerciselist.getSelectionModel().getSelectedItem();
        SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
        if (selectedExercise != null) {
            // Create a copy of the routineExerciselist items to avoid ConcurrentModificationException
            List<ExerciseForWorkoutRoutineBean> copyList = new ArrayList<>(workoutRoutine.getWorkoutDay(selectedExercise.getDay()).getExerciseList());

            for (ExerciseForWorkoutRoutineBean item : copyList) {
                if (selectedExercise.getName().equals(item.getName())) {
                    //routineExerciselist.remove(item);
                    removeExerciseFromDWorkoutRoutineBean(item);
                    break;
                }
            }
        }
        List<ExerciseForWorkoutRoutineBean> activeExercises = new ArrayList<>();
        for (ExerciseForWorkoutRoutineBean exercise : workoutRoutine.getWorkoutDay(selectedExercise.getDay()).getExerciseBeanList()) {
            if (exercise.getStatusExercise() == ExerciseStatusBean.ACTIVE) {
                activeExercises.add(exercise);
            }
        }

        // Set the filtered list in the ListView
        routineExerciselist.getItems().setAll(activeExercises);
        setVisibleLabel(false);
        setVisibleCancel(false);
        resetSelection(2);
        exerciseDBList.getSelectionModel().clearSelection();
        routineExerciselist.getSelectionModel().clearSelection();
    }


    @FXML
    void dayButtonAction(ActionEvent event) {
        setVisibleLabel(false);
        setVisibleCancel(false);
        setVisibleAdd(false);
        selectedDay = daysController.dayButtonAction(event);

        WorkoutDayBean selectedWorkoutDay = workoutRoutine.getWorkoutDay(selectedDay);
        List<ExerciseForWorkoutRoutineBean> exercisesToShow = new ArrayList<>();

        if (selectedWorkoutDay != null) {
            for(ExerciseForWorkoutRoutineBean exe : selectedWorkoutDay.getExerciseList()){
                if(exe.getStatusExercise().equals(ExerciseStatusBean.ACTIVE)){
                    exercisesToShow.add(exe);
                }
            }
        } else{
            System.out.println("Il giorno della scheda è vuoto e quindi non esiste.");
        }

        System.out.println("Scheda del giorno:");
        for (ExerciseForWorkoutRoutineBean ex : exercisesToShow) {
            System.out.println(ex.getName() + " con stato: " + ex.getStatusExercise());
        }
        routineExerciselist.getItems().setAll(exercisesToShow);
    }

    public void resetInputCollectors(){
        restTimeComboBox.setValue("00:00");
        spinnerRepetitions.getValueFactory().setValue(0);
        spinnerSets.getValueFactory().setValue(0);
    }

    @FXML public void searchButtonAction() throws UserCastException {
        SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
        List<ExerciseBean> exerciseBeanList = satisfyWorkoutRequestsController.searchExercise(new SearchBean(searchExerciseText.getText()));
        ManageExerciseList.updateListFiltered(exerciseDBList, exerciseBeanList);
    }

    public void updateSelectedExerciseList() {
        //senza questa, quando risetto active, non me li mostra nel nella routine
        for (WorkoutDayBean workoutDayBean : workoutRoutine.getWorkoutDayList()){
            List<ExerciseForWorkoutRoutineBean> list =  workoutDayBean.getExerciseList();
            for(ExerciseForWorkoutRoutineBean exe: list){
                for(ExerciseBean exerciseDBListExercise : exerciseDBList.getItems()){
                    if (exe.getName().equals(exerciseDBListExercise.getName())){
                        exe.setStatusExercise(exerciseDBListExercise.getStatusExercise());
                    }
                }
            }
        }

        List<ExerciseForWorkoutRoutineBean> activeExercises = new ArrayList<>();
        for (ExerciseForWorkoutRoutineBean exercise : workoutRoutine.getWorkoutDay(selectedDay).getExerciseBeanList()) {
            if (exercise.getStatusExercise() == ExerciseStatusBean.ACTIVE) {
                activeExercises.add(exercise);
            }
        }

        // Set the filtered list in the ListView
        routineExerciselist.getItems().setAll(activeExercises);
    }

    public void updateExerciseList() {
        SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
        ManageExerciseList.updateListFiltered(
                exerciseDBList,
                satisfyWorkoutRequestsController.getGymExerciseBean()
        );
    }

    @Override
    public void update(String exercise) {
        updateExerciseList();

        // Update the status of the corresponding exercises in routineExerciselist
        for (WorkoutDayBean entry : workoutRoutine.getWorkoutDayList()) {
            List<ExerciseForWorkoutRoutineBean> exerciseList = entry.getExerciseList();

            for (ExerciseForWorkoutRoutineBean ex : exerciseList) {
                if (ex.getName().equals(exercise)) {
                    ex.setStatusExercise(ExerciseStatusBean.SUSPENDED);
                }
            }
        }

        updateSelectedExerciseList();
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