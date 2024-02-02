package viewone.graphicalcontrollers.pt;

import beans.*;
import controllers.UserAccessController;
import engineering.LoggedTrainerSingleton;
import engineering.Observer;
import exceptions.EmptySearchException;
import exceptions.NoLoggedUserException;
import exceptions.dataException.DataFieldException;
import exceptions.logger.CostumeLogger;
import viewone.managelistview.listcells.ExerciseForWOListCellFactory;
import viewone.managelistview.listcells.ExerciseListCellFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import viewone.managelistview.ManageExerciseList;
import model.Exercise;
import model.ExerciseStatus;
import utils.MainStage;
import utils.SwitchPage;
import viewone.DaysOfTheWeekButtonController;
import controllers.SatisfyWorkoutRequestsController;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class CreateNewWorkoutRoutineGUIController implements Initializable, Observer {

    @FXML private Button mondayButton;
    public final DaysOfTheWeekButtonController daysController = new DaysOfTheWeekButtonController();
    private RequestBean requestBean;
    private String selectedDay;
    private final WorkoutRoutineBean workoutRoutine = new WorkoutRoutineBean();
    private static final String MY_XML_FILE ="CreateNewWorkoutRoutine.fxml";
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
    @FXML private Label repeatLabelExerciseInserted;
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
        repeatLabelExerciseInserted.setVisible(bool);
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
        UserAccessController logoutController=new UserAccessController();
        logoutController.logout();
        SwitchPage.setStage(MainStage.getStage(),"Login.fxml","launcher",1);
    }
    @FXML
    public void setExerciseStatus() throws Exception{
        SwitchPage.saveElement(MY_XML_FILE,"pt",labelRest.getScene(),this);
        SwitchPage.setStage(MainStage.getStage(),"SetExerciseStatus.fxml","pt",1);
    }
    @FXML
    public void deleteChanges() throws Exception{
        SwitchPage.deleteElement(MY_XML_FILE,"pt");
        SwitchPage.setStage(MainStage.getStage(),"ViewWorkoutRoutineRequests.fxml","pt",1);
    }
    @FXML
    public void goHome() throws Exception{
        SwitchPage.deleteElement(MY_XML_FILE,"pt");
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }
    @FXML
    public void submitRoutine() throws IOException {
        PersonalizeWorkoutRoutineGUIController specializedController = (PersonalizeWorkoutRoutineGUIController) SwitchPage.setStage(MainStage.getStage(),"PersonalizeWorkoutRoutine.fxml","pt",1);
        Objects.requireNonNull(specializedController).setValue(requestBean, this.workoutRoutine);
    }

    public void setValue(RequestBean request){
        try{
            SatisfyWorkoutRequestsController controller = new SatisfyWorkoutRequestsController();
            this.requestBean = request;
            List<ExerciseBean> exerciseBeanList;
            exerciseBeanList = controller.getLoggedTrainerGymExercises();
            for (Exercise ex : LoggedTrainerSingleton.getSingleton().getExcerciseList()) {
                ex.addObserver(this);
            }
            ManageExerciseList.setListenerDB(exerciseDBList, this);
            ManageExerciseList.setListenerRoutineWorkoutTrainer(routineExerciselist, this);
            ManageExerciseList.updateListFiltered(exerciseDBList, exerciseBeanList);
            mondayButton.fire();
            athletesNameRoutineText.setText(requestBean.getAthleteBean().getUsername() + "s' Routine");
        } catch (NoLoggedUserException e){
            e.callMe(1);
        }
    }

    public void setExerciseDetails(int repetitions, int sets, String rest){
        repeatLabelExerciseInserted.setText(Integer.toString(repetitions));
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

            ExerciseForWorkoutRoutineBean newExercise =
                    new ExerciseForWorkoutRoutineBean(selectedExercise.getName(),
                            selectedExercise.getStatusExercise(),
                            selectedDay);
            try{
                newExercise.setRepetitions(repetitions);
                newExercise.setSets(sets);
                newExercise.setRest(rest);
                WorkoutDayBean workoutDay = workoutRoutine.getWorkoutDay(newExercise.getDay());
                if (workoutDay == null) {
                    workoutDay = new WorkoutDayBean(newExercise.getDay());
                    workoutRoutine.addWorkoutDayBean(workoutDay);
                }
                workoutDay.addExerciseBean(newExercise);
            } catch (DataFieldException e){
                cleanGuiAfterAdd();
                e.callMe(1);
                return;
            }
            List<ExerciseForWorkoutRoutineBean> activeExercises = new ArrayList<>();
            for (ExerciseForWorkoutRoutineBean exercise : workoutRoutine.getWorkoutDay(newExercise.getDay()).getExerciseBeanList()) {
                if (exercise.getStatusExercise() == ExerciseStatus.ACTIVE) {
                    activeExercises.add(exercise);
                }
            }
            routineExerciselist.getItems().setAll(activeExercises);
        }
        cleanGuiAfterAdd();
    }

    void cleanGuiAfterAdd(){
        resetSelection(1);
        exerciseDBList.getSelectionModel().clearSelection();
        routineExerciselist.getSelectionModel().clearSelection();
    }

    public void removeExerciseFromDWorkoutRoutineBean (ExerciseForWorkoutRoutineBean exercise) {
        workoutRoutine.getWorkoutDay(exercise.getDay()).removeExerciseBean(exercise);
    }

    @FXML
    public void cancelExercise() {
        ExerciseForWorkoutRoutineBean selectedExercise = routineExerciselist.getSelectionModel().getSelectedItem();
        List<ExerciseForWorkoutRoutineBean> copyList = new ArrayList<>(workoutRoutine.getWorkoutDay(selectedExercise.getDay()).getExerciseList());
        for (ExerciseForWorkoutRoutineBean item : copyList) {
            if (selectedExercise.getName().equals(item.getName())) {
                removeExerciseFromDWorkoutRoutineBean(item);
                break;
            }
        }
        List<ExerciseForWorkoutRoutineBean> activeExercises = new ArrayList<>();
        for (ExerciseForWorkoutRoutineBean exercise : workoutRoutine.getWorkoutDay(selectedExercise.getDay()).getExerciseBeanList()) {
            if (exercise.getStatusExercise() == ExerciseStatus.ACTIVE) {
                activeExercises.add(exercise);
                }
        }
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
                if(exe.getStatusExercise().equals(ExerciseStatus.ACTIVE)){
                    exercisesToShow.add(exe);
                }
            }
        }
        routineExerciselist.getItems().setAll(exercisesToShow);
    }

    public void resetInputCollectors(){
        restTimeComboBox.setValue("00:00");
        spinnerRepetitions.getValueFactory().setValue(0);
        spinnerSets.getValueFactory().setValue(0);
    }

    @FXML public void searchButtonAction() {
        try {
            SatisfyWorkoutRequestsController controller = new SatisfyWorkoutRequestsController();
            List<ExerciseBean> exerciseBeanList;
            exerciseBeanList = controller.searchExercise(new SearchBean(searchExerciseText.getText()));
            ManageExerciseList.updateListFiltered(exerciseDBList, exerciseBeanList);
        }catch (NoLoggedUserException | EmptySearchException e){
            e.callMe(1);
            CostumeLogger.getInstance().logError(e);
        }
    }

    public void updateSelectedExerciseList() {
        List<ExerciseForWorkoutRoutineBean> activeExercises = new ArrayList<>();
        WorkoutDayBean selectedWorkoutDay = workoutRoutine.getWorkoutDay(selectedDay);
        if (selectedWorkoutDay != null) {
            for (ExerciseForWorkoutRoutineBean exercise : selectedWorkoutDay.getExerciseBeanList()) {
                if (exercise.getStatusExercise() == ExerciseStatus.ACTIVE) {
                    activeExercises.add(exercise);
                }
            }
        }
        routineExerciselist.getItems().setAll(activeExercises);
    }


    public void updateExerciseList() {
        try {
            SatisfyWorkoutRequestsController controller = new SatisfyWorkoutRequestsController();
            List<ExerciseBean> listBean;
            listBean=controller.getLoggedTrainerGymExercises();
            ManageExerciseList.updateListFiltered(exerciseDBList,listBean);
        }catch (NoLoggedUserException e){
            e.callMe(1);
        }

    }

    @Override
    public void update(String exercise, ExerciseStatus status) {
        updateExerciseList();
        for (WorkoutDayBean entry : workoutRoutine.getWorkoutDayList()) {
            List<ExerciseForWorkoutRoutineBean> exerciseList = entry.getExerciseList();

            for (ExerciseForWorkoutRoutineBean ex : exerciseList) {
                if (ex.getName().equals(exercise)) {
                    ex.setStatusExercise(status);
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