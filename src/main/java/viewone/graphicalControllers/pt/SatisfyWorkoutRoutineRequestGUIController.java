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
import model.WorkoutDay;
import utils.MainStage;
import utils.SwitchPage;
import viewone.DayBeanA;
import viewone.DaysOfTheWeekButtonController;
import controllers.SatisfyWorkoutRequestsController;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class SatisfyWorkoutRoutineRequestGUIController implements Initializable, Observer {

    @FXML private Button mondayButton;
    public final DaysOfTheWeekButtonController daysController = new DaysOfTheWeekButtonController();
    private RequestBean requestBean;
    private String selectedDay;
    private final WorkoutRoutineBean workoutRoutine = new WorkoutRoutineBean(); //TODO replace hash map with this

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
        SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
        satisfyWorkoutRequestsController.submitRoutine(requestBean, this.workoutRoutine);
        AddCommentToWorkoutRoutineGUIController controller = (AddCommentToWorkoutRoutineGUIController) SwitchPage.setStage(MainStage.getStage(),"AddCommentToWorkoutRoutine.fxml","pt",1);
        Objects.requireNonNull(controller).setValue(requestBean, satisfyWorkoutRequestsController);

    }

    public void setValue(RequestBean request) throws UserCastException {
        this.requestBean = request;
        List<ExerciseBean> exerciseBeanList;
        SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
        try{
            exerciseBeanList = satisfyWorkoutRequestsController.getGymExerciseBean();
            for (Exercise ex : LoggedUserSingleton.getSingleton().getExcerciseInventory().getExerciseList()) {
                ex.addObserver(this);
            }

        }catch (UserCastException exception1){ //TODO valuta se togliere UserCastException
            try {
                exception1.callMe(1);
            }catch(IOException exception2){
                return ;
            }
            return;
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
        SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
        if (selectedExercise != null && selectedDay != null) {
            setVisibleLabel(false);
            setVisibleAdd(false);

            int repetitions = spinnerRepetitions.getValue();
            int sets = spinnerSets.getValue();
            String rest = restTimeComboBox.getValue();
            boolean added = true;

            ExerciseForWorkoutRoutineBean newExercise =
                    new ExerciseForWorkoutRoutineBean(selectedExercise.getName(),
                            selectedExercise.getStatusExercise(),
                            selectedDay);
            newExercise.setRepetitions(repetitions);
            newExercise.setSets(sets);
            if (!newExercise.setRest(rest)) {
                added = false;
            }
            List<ExerciseForWorkoutRoutineBean> workoutDay= new ArrayList<>();
            workoutDay.addAll(routineExerciselist.getItems());
            added = satisfyWorkoutRequestsController.addExerciseToWorkoutDay(newExercise, workoutDay);
            if (added) {
                routineExerciselist.getItems().add(newExercise);
                if(this.workoutRoutine.getWorkoutDay(newExercise.getDay()) != null){
                    this.workoutRoutine.getWorkoutDay(newExercise.getDay()).addExerciseBean(newExercise);
                } else {
                    this.workoutRoutine.addWorkoutDayBean(new WorkoutDayBean(newExercise.getDay()));
                    this.workoutRoutine.getWorkoutDay(newExercise.getDay()).addExerciseBean(newExercise);
                    /*//TODO giorno nullo
                    System.out.println("Exercise was not added successfully, because the day returned was null.");*/
                }
            }else {
                System.out.println("Exercise was not added successfully.");
            }

            resetSelection(1);
            exerciseDBList.getSelectionModel().clearSelection();
            routineExerciselist.getSelectionModel().clearSelection();
        }
    }

    @FXML
    public void cancelExercise() {
        ExerciseForWorkoutRoutineBean selectedExercise = routineExerciselist.getSelectionModel().getSelectedItem();
        SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
        if (selectedExercise != null) {
            satisfyWorkoutRequestsController.removeExerciseToWorkoutDay(selectedExercise, routineExerciselist, workoutRoutine);

            // Remove the exercise from the dayExercisesMap
            List<ExerciseForWorkoutRoutineBean> dayExercises = workoutRoutine.getWorkoutDay(selectedExercise.getDay()).getExerciseBeanList();
            if (dayExercises != null) {
                dayExercises.removeIf(dayExercise -> dayExercise.getName().equals(selectedExercise.getName()));
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
        /*for (WorkoutDayBean workoutDayBean : workoutRoutine.getWorkoutDayList()){
            ManageExerciseList.updateRoutineList(
                    routineExerciselist,
                    workoutDayBean.getExerciseBeanList());
        }*/
        for (WorkoutDayBean workoutDayBean : workoutRoutine.getWorkoutDayList()){
            List<ExerciseForWorkoutRoutineBean> list =  workoutDayBean.getExerciseList();
            for(ExerciseForWorkoutRoutineBean exe: list){
                for(ExerciseBean exerciseDBLisExercise : exerciseDBList.getItems()){
                    if (exe.getName().equals(exerciseDBLisExercise.getName())){
                        exe.setStatusExercise(exerciseDBLisExercise.getStatusExercise());
                    }
                }

            }
        }
        ManageExerciseList.updateListFilteredDB(
                routineExerciselist,
                exerciseDBList.getItems());
        /*SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
        WorkoutDayBean workoutDayBean = satisfyWorkoutRequestsController.getWorkoutDayBean(new DayBeanA(daysController.getDay()));*/

    }

    public void updateExerciseList() {
        SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
        try {
            ManageExerciseList.updateListFiltered(
                    exerciseDBList,
                    satisfyWorkoutRequestsController.getGymExerciseBean()
            );
        } catch(UserCastException e){
            return;
        }
    }

    @Override
    public void update(String exercise) {

        updateExerciseList();

        // Update the status of the corresponding exercise in routineExerciselist
        for (WorkoutDayBean entry : workoutRoutine.getWorkoutDayList()) {
            List<ExerciseForWorkoutRoutineBean> exerciseList = entry.getExerciseList();

            for (ExerciseForWorkoutRoutineBean ex : exerciseList) {
                if (ex.getName().equals(exercise)) {
                    ex.setStatusExercise(ExerciseStatusBean.SUSPENDED);
                }
            }
        }

        // Refresh the routineExerciselist
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