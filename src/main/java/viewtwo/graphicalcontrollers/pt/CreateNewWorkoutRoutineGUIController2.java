package viewtwo.graphicalcontrollers.pt;

import beans.*;
import exceptions.CostumException;
import exceptions.NoDayIsSelectedException;
import exceptions.NoLoggedUserException;
import exceptions.dataException.DataFieldException;
import exceptions.logger.CostumeLogger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import controllers.SatisfyWorkoutRequestsController;
import engineering.LoggedTrainerSingleton;
import engineering.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Exercise;
import model.ExerciseStatus;
import viewtwo.manageListView.ManageExerciseList2;
import viewtwo.manageListView.listCells.ExerciseForWOListCellFactory2;
import viewtwo.manageListView.listCells.ExerciseListCellFactory2;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CreateNewWorkoutRoutineGUIController2 implements Initializable, Observer {
    @FXML private Label repetitionsLabelNumber;
    @FXML private Label setsLabelNumber;
    @FXML private Label restLabelNumber;
    @FXML private Label repetitionLabel;
    @FXML private Label setsLabel;
    @FXML private Label restLabel;
    @FXML private Button removeButton;
    @FXML private Button addButton;
    @FXML private TextField restTextField;
    @FXML private TextField repTextField;
    @FXML private TextField setsTextField;
    private String selectedDay;
    private final List<String> dayList = new ArrayList<>();
    @FXML private RequestBean requestBean;
    @FXML private ListView <ExerciseBean> exerciseDBList2;
    @FXML private ListView<ExerciseForWorkoutRoutineBean> routineExerciselist2;
    @FXML private List<RadioButton> radioButtonList;
    @FXML private RadioButton mondayRadioButton;
    @FXML private RadioButton tuesdayRadioButton;
    @FXML private RadioButton wednesdayRadioButton;
    @FXML private RadioButton thursdayRadioButton;
    @FXML private RadioButton fridayRadioButton;
    @FXML private RadioButton saturdayRadioButton;
    @FXML private RadioButton sundayRadioButton;
    private WorkoutRoutineBean workoutRoutine;

    public void setVisibleAdd(boolean bool){
        addButton.setVisible(bool);
        setsTextField.setVisible(bool);
        restTextField.setVisible(bool);
        repTextField.setVisible(bool);
    }
    public void setVisibleLabel(boolean bool){
        repetitionLabel.setVisible(bool);
        setsLabel.setVisible(bool);
        restLabel.setVisible(bool);
    }
    public void resetSelection(int choice){
        if(choice == 1){
            routineExerciselist2.getSelectionModel().clearSelection();
        } else {
            exerciseDBList2.getSelectionModel().clearSelection();
        }
    }
    public void setVisibleCancel(boolean bool) {
        removeButton.setVisible(bool);
        repetitionsLabelNumber.setVisible(bool);
        setsLabelNumber.setVisible(bool);
        restLabelNumber.setVisible(bool);
    }

    private int getIntDay() {
        for(int i = 0; i < 7; i++) {
            if(radioButtonList.get(i).isSelected()) {
                return i;
            }
        }
        return 0;
    }

    private int getDay() throws NoDayIsSelectedException {
        for(int i = 0; i < 7; i++) {
            if(radioButtonList.get(i).isSelected()) {
                return i+1;
            }
        }
        throw new NoDayIsSelectedException();
    }
    private void setListener1(ListView<ExerciseBean> exerciseList) {
        exerciseList.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends ExerciseBean> observableValue, ExerciseBean oldItem, ExerciseBean newItem) -> listEventDB(newItem)
        );
    }
    private void listEventDB(ExerciseBean newItem) {
        if (newItem != null) {
            setVisibleLabel(true);
            setVisibleAdd(true);
            setVisibleCancel(false);
            //resetInputCollectors();
            resetSelection(1);
        }
    }

    private void setListener2(ListView<ExerciseForWorkoutRoutineBean> exerciseRoutineList) {
        exerciseRoutineList.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends ExerciseForWorkoutRoutineBean> observableValue, ExerciseForWorkoutRoutineBean oldItem, ExerciseForWorkoutRoutineBean newItem) -> listEventRoutineWorkout(newItem)
        );
    }

    private  void listEventRoutineWorkout(ExerciseForWorkoutRoutineBean newItem) {
        if (newItem != null) {
            //setExerciseDetails(newItem.getRepetitions(), newItem.getSets(), newItem.getRest());
            setVisibleLabel(true);
            setVisibleAdd(false);
            setVisibleCancel(true);
            resetSelection(2);
        }
    }

    public void setValue(RequestBean request){
        this.requestBean = request;
        SatisfyWorkoutRequestsController controller;
        try{
            controller = new SatisfyWorkoutRequestsController();
        } catch (NoLoggedUserException e){
            try {
                e.callMe(1);
                return;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        List<ExerciseBean> exerciseBeanList = controller.getLoggedTrainerGymExercises();
        for (Exercise exer : LoggedTrainerSingleton.getSingleton().getExcerciseList()) {
            exer.addObserver(this);
        }

        /* TODO METTI LISTNER CHE TI APRONO UN ALTRA SCHERMATA E TI PERMETTONO DI GESTIRE I DATI
        ManageExerciseList2.setListenerDB(exerciseDBList2, this);
        ManageExerciseList2.setListenerRoutineWorkout(routineExerciselist2, this);*/

        ManageExerciseList2.updateListFiltered(exerciseDBList2, exerciseBeanList);
        ManageExerciseList2.updateListFilteredDB(routineExerciselist2, exerciseBeanList);
    }

    /*public void setVisibleLabel(Boolean bool) {
        labelSets.setVisible(bool);
        labelRepetitions.setVisible(bool);
        labelRest.setVisible(bool);
    }*/
    public void emptySelectedExerciseList() {
        routineExerciselist2.setItems(FXCollections.observableList(new ArrayList<>()));
    }
    @FXML void dayButtonAction(ActionEvent event) {
        try {
            if (((RadioButton) event.getSource()).isSelected()) {
                updateSelectedExerciseList();
            } else {
                emptySelectedExerciseList();
            }
        } catch (NoDayIsSelectedException e) {
            try {
                e.callMe(1);
            } catch (IOException ex) {
                CostumeLogger.getInstance().logError(ex);
            }
        }
    }

    public void updateSelectedExerciseList() throws NoDayIsSelectedException {
        try{
            WorkoutDayBean workoutDayBean = workoutRoutine.getWorkoutDay(selectedDay);
            ObservableList<ExerciseForWorkoutRoutineBean> exerciseBeanObservableList = FXCollections.observableList(workoutDayBean.getExerciseBeanList());
            routineExerciselist2.setItems(exerciseBeanObservableList);
        } catch (NullPointerException e){
            CostumeLogger.getInstance().logError(e);
        }
    }

    @FXML
    public void addExercise() {
        ExerciseBean selectedExercise = exerciseDBList2.getSelectionModel().getSelectedItem();

        if (selectedExercise == null || selectedDay == null) {
            cleanGuiAfterAdd();
            return;
        }

        setVisibleLabel(false);
        setVisibleAdd(false);

        ExerciseForWorkoutRoutineBean newExercise = createExercise(selectedExercise);

        if (newExercise == null) {
            cleanGuiAfterAdd();
            return;
        }

        WorkoutDayBean workoutDay = getOrCreateWorkoutDay(newExercise);

        try {
            workoutDay.addExerciseBean(newExercise);
        } catch (DataFieldException e) {
            cleanGuiAfterAdd();
            try {
                e.callMe(1);
            } catch (IOException ignore) {}
        }

        List<ExerciseForWorkoutRoutineBean> activeExercises = getActiveExercises(newExercise.getDay());
        routineExerciselist2.getItems().setAll(activeExercises);

        cleanGuiAfterAdd();
    }

    private ExerciseForWorkoutRoutineBean createExercise(ExerciseBean selectedExercise) {
        //todo mostra ad alex come gestire le TUTTE eccezioni
        ExerciseForWorkoutRoutineBean newExercise = new ExerciseForWorkoutRoutineBean(
                selectedExercise.getName(),
                selectedExercise.getStatusExercise(),
                selectedDay
        );

        String repsString = repTextField.getText();
        String setsString = setsTextField.getText();
        String rest = restTextField.getText();
        try {
            int repetitions = Integer.parseInt(repsString);
            int sets = Integer.parseInt(setsString);
            newExercise.setRepetitions(repetitions);
            newExercise.setSets(sets);
            newExercise.setRest(rest);
            return newExercise;
        } catch (NumberFormatException e) {
            CostumeLogger.getInstance().logError(e);
            return null;
        }catch (DataFieldException e){
            try {
                e.callMe(1);
                return null;
            } catch (IOException ex) {
                CostumeLogger.getInstance().logError(ex);
                return null;
            }
        }
    }

    private WorkoutDayBean getOrCreateWorkoutDay(ExerciseForWorkoutRoutineBean newExercise) {
        WorkoutDayBean workoutDay = workoutRoutine.getWorkoutDay(newExercise.getDay());
        if (workoutDay == null) {
            workoutDay = new WorkoutDayBean(newExercise.getDay());
            workoutRoutine.addWorkoutDayBean(workoutDay);
        }
        return workoutDay;
    }

    private List<ExerciseForWorkoutRoutineBean> getActiveExercises(String day) {
        List<ExerciseForWorkoutRoutineBean> activeExercises = new ArrayList<>();
        WorkoutDayBean workoutDay = workoutRoutine.getWorkoutDay(day);

        if (workoutDay != null) {
            for (ExerciseForWorkoutRoutineBean exercise : workoutDay.getExerciseBeanList()) {
                if (exercise.getStatusExercise() == ExerciseStatus.ACTIVE) {
                    activeExercises.add(exercise);
                }
            }
        }
        return activeExercises;
    }

    private void cleanGuiAfterAdd() {
        resetSelection(1);
        exerciseDBList2.getSelectionModel().clearSelection();
        routineExerciselist2.getSelectionModel().clearSelection();
        setVisibleLabel(false);
        setVisibleAdd(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.workoutRoutine = new WorkoutRoutineBean();
        setVisibleAdd(false);
        setVisibleLabel(false);
        setVisibleCancel(false);
        exerciseDBList2.setCellFactory(new ExerciseListCellFactory2());
        routineExerciselist2.setCellFactory(new ExerciseForWOListCellFactory2());

        radioButtonList = Arrays.asList(
                mondayRadioButton,
                tuesdayRadioButton,
                wednesdayRadioButton,
                thursdayRadioButton,
                fridayRadioButton,
                saturdayRadioButton,
                sundayRadioButton
        );

        ToggleGroup group = new ToggleGroup();
        mondayRadioButton.setToggleGroup(group);
        tuesdayRadioButton.setToggleGroup(group);
        wednesdayRadioButton.setToggleGroup(group);
        thursdayRadioButton.setToggleGroup(group);
        fridayRadioButton.setToggleGroup(group);
        saturdayRadioButton.setToggleGroup(group);
        sundayRadioButton.setToggleGroup(group);

        mondayRadioButton.fire();


        for(int i = 1; i <= 7; i++) {
            dayList.add(DayOfWeek.of(i).name());
        }

    }

    @Override
    public void update(String exerciseName, ExerciseStatus status) {

    }

    public void changeStatus(ActionEvent actionEvent) {
        //todo da fare
    }
}
