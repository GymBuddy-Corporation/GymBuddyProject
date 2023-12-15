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
import viewone.DayBeanA;
import viewone.DaysOfTheWeekButtonController;
import controllers.SatisfyWorkoutRequestsController;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SatisfyWorkoutRoutineRequestGUIController implements Initializable, Observer {

    @FXML private Button mondayButton;
    private Map<String, List<ExerciseForWorkoutRoutineBean>> dayExercisesMap = new HashMap<>();
    public final DaysOfTheWeekButtonController daysController = new DaysOfTheWeekButtonController();

    private RequestBean requestBean;
    private String selectedDay;
    private String statusToUpdate;

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
        SwitchPage.saveElement("SatisfyWorkoutRoutineRequest.fxml","pt",labelRest.getScene(),this);
        SetExerciseStatusGUIController controller = (SetExerciseStatusGUIController) SwitchPage.setStage(MainStage.getStage(),"SetExerciseStatus.fxml","pt",1);
        Objects.requireNonNull(controller).setValue(requestBean, satisfyWorkoutRequestsController, dayExercisesMap, this);
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
        satisfyWorkoutRequestsController.submitRoutine(requestBean);
        AddCommentToWorkoutRoutineGUIController controller = (AddCommentToWorkoutRoutineGUIController) SwitchPage.setStage(MainStage.getStage(),"AddCommentToWorkoutRoutine.fxml","pt",1);
        Objects.requireNonNull(controller).setValue(requestBean, satisfyWorkoutRequestsController);

    }

    public void setValue(RequestBean request, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) throws UserCastException {
        this.requestBean = request;
        this.satisfyWorkoutRequestsController = satisfyWorkoutRequestsController;
        setStuff(satisfyWorkoutRequestsController);
    }

    public void setBackValue(RequestBean request, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, Map<String, List<ExerciseForWorkoutRoutineBean>> dayExercisesMap, String newStatus) {
        this.requestBean = request;
        this.statusToUpdate = newStatus;
        this.dayExercisesMap=dayExercisesMap;
        //setStuff(satisfyWorkoutRequestsController);
    }

    public void setStuff(SatisfyWorkoutRequestsController satisfyWorkoutRequestsController){
        List<ExerciseBean> exerciseBeanList;
        List<Exercise> observedList;
        try{
             exerciseBeanList = satisfyWorkoutRequestsController.getGymExerciseBean();
             observedList= LoggedUserSingleton.getSingleton().getExcerciseInventory().getExerciseList();

        }catch (UserCastException exception1){ //TODO valuta exception UserCastException
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
        if (observedList != null){
            for (Exercise ex : observedList) {
                ex.addObserver(this);
            }
        }
    }

    public boolean checkAlreadyAdded(ExerciseForWorkoutRoutineBean exerciseForWorkoutRoutineBean) {
        for (ExerciseForWorkoutRoutineBean exercise : routineExerciselist.getItems()/*workoutDayBean.getExerciseBeanList()*/) {
            /*Il commento serve perchè poi scorreremo tutto il giorno e vedremo se c'è SOLO per quel giorno*/
            if (Objects.equals(exercise.getName(), exerciseForWorkoutRoutineBean.getName())) {
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
        //TODO sistema il lancio dell'eccezione (non so come farlo, e questo schifo che ho fatto non funziona)
        /*try {*/
        ExerciseBean selectedExercise = exerciseDBList.getSelectionModel().getSelectedItem();

        if (selectedExercise != null && selectedDay != null) {

            setVisibleLabel(false);
            setVisibleAdd(false);

            int repetitions = spinnerRepetitions.getValue();
            int sets = spinnerSets.getValue();
            String rest = restTimeComboBox.getValue();
            boolean test = true;

            ExerciseForWorkoutRoutineBean newExercise =
                    new ExerciseForWorkoutRoutineBean(selectedExercise.getName(),
                            selectedExercise.getStatusExercise(),
                            selectedDay);

            if (!checkAlreadyAdded(newExercise) && sets != 0 && repetitions != 0) {
                newExercise.setRepetitions(repetitions);
                newExercise.setSets(sets);
                if (!newExercise.setRest(rest)) {
                    test = false;
                }
                if (test) {
                    List<ExerciseForWorkoutRoutineBean> workoutDay= new ArrayList<>();
                    workoutDay.addAll(routineExerciselist.getItems());
                    satisfyWorkoutRequestsController.addExerciseToWorkoutDay(newExercise, workoutDay);
                    // Update the map with the new exercise
                    routineExerciselist.getItems().add(newExercise);
                    dayExercisesMap.computeIfAbsent(newExercise.getDay(), k -> new ArrayList<>()).add(newExercise);
                } /*else {
                        throw new InvalidExerciseFeaturesException("??");
                    }*/
            }
            resetSelection(1);
            exerciseDBList.getSelectionModel().clearSelection();
            routineExerciselist.getSelectionModel().clearSelection();
        }
        /*} catch (InvalidExerciseFeaturesException e) {
            // Handle the exception, e.g., show an alert or log the error
            e.printStackTrace();
        }*/
    }

    @FXML
    public void cancelExercise() {
        ExerciseForWorkoutRoutineBean selectedExercise = routineExerciselist.getSelectionModel().getSelectedItem();
        if (selectedExercise != null) {
            satisfyWorkoutRequestsController.removeExerciseToWorkoutDay(selectedExercise, routineExerciselist, dayExercisesMap);

            // Remove the exercise from the dayExercisesMap
            List<ExerciseForWorkoutRoutineBean> dayExercises = dayExercisesMap.get(selectedExercise.getDay());
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

        // If exercises for the selected day are not loaded, load them
        if (!dayExercisesMap.containsKey(selectedDay)) {
            dayExercisesMap.put(selectedDay, new ArrayList<>());
        }

        // Update the RoutineExerciselist with exercises for the selected day
        routineExerciselist.getItems().setAll(dayExercisesMap.get(selectedDay));
    }

    public void resetInputCollectors(){
        restTimeComboBox.setValue("00:00");
        spinnerRepetitions.getValueFactory().setValue(0);
        spinnerSets.getValueFactory().setValue(0);
    }

    @FXML public void searchButtonAction() throws UserCastException {
        List<ExerciseBean> exerciseBeanList = satisfyWorkoutRequestsController.searchExercise(new SearchBean(searchExerciseText.getText()));
        ManageExerciseList.updateListFiltered(exerciseDBList, exerciseBeanList);
    }

    public void updateSelectedExerciseList() {
        WorkoutDayBean workoutDayBean = satisfyWorkoutRequestsController.getWorkoutDayBean(new DayBeanA(daysController.getDay()));
        ManageExerciseList.updateRoutineList(
                routineExerciselist,
                workoutDayBean.getExerciseBeanList());
    }

    public void updateExerciseList() {
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
        // TODO togli il commentato quando risolvo
        for(ExerciseBean ex : exerciseDBList.getItems()){
            if(ex.getName().equals(exercise)){
                if(statusToUpdate.equals("Active")){
                    ex.setStatusExercise(ExerciseStatusBean.ACTIVE);
                } else if (statusToUpdate.equals("Suspended")){
                    ex.setStatusExercise(ExerciseStatusBean.SUSPENDED);
                } else {
                    System.out.println("Eccezione1 exerciseDBList ");
                    //throw exception
                }
            }
        }
        updateExerciseList();

        for (Map.Entry<String, List<ExerciseForWorkoutRoutineBean>> entry : dayExercisesMap.entrySet()) {
            List<ExerciseForWorkoutRoutineBean> exerciseList = entry.getValue();

            for (ExerciseForWorkoutRoutineBean ex : exerciseList) {
                if (ex.getName().equals(exercise)) {
                    if(statusToUpdate.equals("Active")){
                        ex.setStatusExercise(ExerciseStatusBean.ACTIVE);
                    } else if (statusToUpdate.equals("Suspended")){
                        ex.setStatusExercise(ExerciseStatusBean.SUSPENDED);
                    } else {
                        System.out.println("Eccezione1 exerciseDBList ");
                        //throw exception
                    }
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