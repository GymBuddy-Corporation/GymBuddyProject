package viewtwo.graphicalcontrollers.pt;

import beans.*;
import exceptions.NoDayIsSelectedException;
import exceptions.NoLoggedUserException;
import exceptions.dataException.DataFieldException;
import exceptions.logger.CostumeLogger;
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
import viewtwo.popups.abstracts.AddExeInterface;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.System.exit;

public class CreateNewWorkoutRoutineGUIController2 implements Initializable, Observer, AddExeInterface {
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

    public String getSelectedDay(){
        return selectedDay;
    }

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

    private int getDay() throws NoDayIsSelectedException {
        for(int i = 0; i < 7; i++) {
            if(radioButtonList.get(i).isSelected()) {
                return i+1;
            }
        }
        throw new NoDayIsSelectedException();
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

        ManageExerciseList2.setListenerDB(exerciseDBList2, this);
        /* TODO METTI LISTNER CHE TI APRONO UN ALTRA SCHERMATA E TI PERMETTONO DI GESTIRE I DATI*/
        ManageExerciseList2.setListenerRoutineWorkout(routineExerciselist2, this);

        ManageExerciseList2.updateListFiltered(exerciseDBList2, exerciseBeanList);
        mondayRadioButton.fire();
    }

    @FXML void dayButtonAction(ActionEvent event) {
        try {
            if (((RadioButton) event.getSource()).isSelected()) {
                selectedDay = getNameDay();
                updateSelectedExerciseList();
            }
        } catch (NoDayIsSelectedException e) {
            try {
                e.callMe(1);
            } catch (IOException ex) {
                CostumeLogger.getInstance().logError(ex);
            }
        }
    }

    private String getNameDay() throws NoDayIsSelectedException {
        return switch (getDay()) {
            case 1 -> "MONDAY";
            case 2 -> "TUESDAY";
            case 3 -> "WEDNESDAY";
            case 4 -> "THURSDAY";
            case 5 -> "FRIDAY";
            case 6 -> "SATURDAY";
            case 7 -> "SUNDAY";
            default -> null;
        };
    }

    public void updateSelectedExerciseList() throws NoDayIsSelectedException {
        try{
            if(selectedDay == null){
                throw new NoDayIsSelectedException();
            }
            System.out.println("selectedDay11111: " + selectedDay);
            WorkoutDayBean workoutDayBean = workoutRoutine.getWorkoutDay(selectedDay);

            for(ExerciseForWorkoutRoutineBean ex : workoutDayBean.getExerciseBeanList()){
                System.out.println("Esercizio nel giorno: " + ex.getName());
            }

            List<ExerciseForWorkoutRoutineBean> listActiveExercises = new ArrayList<>();
            for (ExerciseForWorkoutRoutineBean exe : workoutDayBean.getExerciseList()) {
                if (exe.getStatusExercise() == ExerciseStatus.ACTIVE) {
                    listActiveExercises.add(exe);
                }
            }
            routineExerciselist2.getItems().setAll(listActiveExercises);
        } catch (NullPointerException e){
            routineExerciselist2.getItems().clear();
        }
    }

    private WorkoutDayBean getOrCreateWorkoutDay(ExerciseForWorkoutRoutineBean newExercise) {
        WorkoutDayBean workoutDay = workoutRoutine.getWorkoutDay(newExercise.getDay());
        if (workoutDay == null) {
            workoutDay = new WorkoutDayBean(newExercise.getDay());
            System.out.println("Sto aggiungendo lesercizio: " + newExercise.getName() + " nel giorno " + workoutDay.getName());
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

        for(int i = 1; i <= 7; i++) {
            dayList.add(DayOfWeek.of(i).name());
        }

    }

    @Override
    public void update(String exerciseName, ExerciseStatus status) {
        //todo da fare
    }

    public void changeStatus(ActionEvent actionEvent) {
        //todo da fare
    }

    @Override
    public void addExercise(ExerciseForWorkoutRoutineBean bean) {
        WorkoutDayBean workoutDay = getOrCreateWorkoutDay(bean);
        try {
            workoutDay.addExerciseBean(bean);
        } catch (DataFieldException e) {
            try {
                e.callMe(1);
            } catch (IOException ex) {
                CostumeLogger.getInstance().logError(e);
            }
        }

        List<ExerciseForWorkoutRoutineBean> activeExercises = getActiveExercises(bean.getDay());
        routineExerciselist2.getItems().setAll(activeExercises);
    }

    public void addExercise(ActionEvent actionEvent) {
        //todo elimina
    }
}
