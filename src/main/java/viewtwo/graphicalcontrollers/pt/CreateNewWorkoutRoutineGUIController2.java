package viewtwo.graphicalcontrollers.pt;

import beans.*;
import exceptions.*;
import exceptions.dataException.DataFieldException;
import exceptions.logger.CostumeLogger;
import javafx.animation.PauseTransition;
import javafx.scene.control.*;
import controllers.SatisfyWorkoutRequestsController;
import engineering.LoggedTrainerSingleton;
import engineering.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.Exercise;
import model.ExerciseStatus;
import viewone.managelistview.ManageExerciseList;
import viewtwo.engegnering.MainMenuSingleton;
import viewtwo.manageListView.ManageExerciseList2;
import viewtwo.manageListView.listCells.ExerciseForWOListCellFactory2;
import viewtwo.manageListView.listCells.ExerciseListCellFactory2;
import viewtwo.popups.ChangeExeStatusPopUp;
import viewtwo.popups.PersonalizeWRPopUp;
import viewtwo.popups.abstracts.AddExeInterface;
import viewtwo.popups.abstracts.ChangeExeStatusInterface;
import viewtwo.popups.abstracts.DeleteExeInterface;
import viewtwo.popups.abstracts.PersonalizeWorkoutRequestInterface;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class CreateNewWorkoutRoutineGUIController2 implements Initializable, Observer, AddExeInterface, DeleteExeInterface, ChangeExeStatusInterface, PersonalizeWorkoutRequestInterface {
    private String selectedDay;
    @FXML private RequestBean requestBean;
    @FXML private ListView <ExerciseBean> exerciseDBList2;
    @FXML private ListView<ExerciseForWorkoutRoutineBean> routineExerciselist2;
    @FXML private List<RadioButton> radioButtonList;
    @FXML private Label updateLabel;
    @FXML private Label labelUser;

    @FXML private RadioButton mondayRadioButton;
    @FXML private RadioButton tuesdayRadioButton;
    @FXML private RadioButton wednesdayRadioButton;
    @FXML private RadioButton thursdayRadioButton;
    @FXML private RadioButton fridayRadioButton;
    @FXML private RadioButton saturdayRadioButton;
    @FXML private RadioButton sundayRadioButton;
    @FXML private TextField searchExerciseText;
    @FXML private ImageView correctSetUp;
    private WorkoutRoutineBean workoutRoutine;

    //todo capire perchè stampa lancia bene solo l'eccezione sul REST sbagliato/nullo, ma non
    // quelli su reps e sets errati/nulli (?)
    public String getSelectedDay(){
        return selectedDay;
    }
    public void resetSelection(int choice){
        if(choice == 1){
            routineExerciselist2.getSelectionModel().clearSelection();
        } else {
            exerciseDBList2.getSelectionModel().clearSelection();
        }
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

        try{
            SatisfyWorkoutRequestsController controller = new SatisfyWorkoutRequestsController();
            this.requestBean = request;
            List<ExerciseBean> exerciseBeanList = controller.getLoggedTrainerGymExercises();
            for (Exercise exer : LoggedTrainerSingleton.getSingleton().getExcerciseList()) {
                exer.addObserver(this);
            }
            ManageExerciseList2.setListenerDB(exerciseDBList2, this);
            ManageExerciseList2.setListenerRoutineWorkout(routineExerciselist2, this);

            ManageExerciseList2.updateListFiltered(exerciseDBList2, exerciseBeanList);
            mondayRadioButton.fire();
            labelUser.setText(requestBean.getAthleteBean().getUsername());
        } catch (NoLoggedUserException e){
           e.callMe(2);
        }
    }

    @FXML void dayButtonAction(ActionEvent event) {
        try {
            if (((RadioButton) event.getSource()).isSelected()) {
                selectedDay = getNameDay();
                updateSelectedExerciseList();
            }
        } catch (NoDayIsSelectedException e) {
                e.callMe(2);
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
            WorkoutDayBean workoutDayBean = workoutRoutine.getWorkoutDay(selectedDay);

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
        exerciseDBList2.setCellFactory(new ExerciseListCellFactory2());
        routineExerciselist2.setCellFactory(new ExerciseForWOListCellFactory2());
        correctSetUp.setVisible(false);
        updateLabel.setVisible(false);

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
    }
    public void changeStatus() {
        try{
            ChangeExeStatusPopUp.getChangeExeStatusPopup(this, "ChangeExerciseStatusPopUp.fxml", "popups", 2);
        } catch (IOException e) {
            CostumeLogger.getInstance().logError(e);
        }
    }

    @Override
    public void addExercise(ExerciseForWorkoutRoutineBean bean) {
        WorkoutDayBean workoutDay = getOrCreateWorkoutDay(bean);
        try {
            workoutDay.addExerciseBean(bean);
        } catch (DataFieldException e) {
                e.callMe(2);
        }
        resetSelection(1);
        List<ExerciseForWorkoutRoutineBean> activeExercises = getActiveExercises(bean.getDay());
        routineExerciselist2.getItems().setAll(activeExercises);
    }

    @Override
    public void deleteExercise(ExerciseForWorkoutRoutineBean bean) {
        WorkoutDayBean workoutDay = getOrCreateWorkoutDay(bean);
        workoutDay.removeExerciseBean(bean);
        List<ExerciseForWorkoutRoutineBean> activeExercises = getActiveExercises(bean.getDay());
        routineExerciselist2.getItems().setAll(activeExercises);
    }
    public void updateExerciseList() {
        try {
            SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
            List<ExerciseBean> listBean2 = satisfyWorkoutRequestsController.getLoggedTrainerGymExercises();
            ManageExerciseList.updateListFiltered( exerciseDBList2, listBean2);
        } catch (NoLoggedUserException e){
            CostumeLogger.getInstance().logError(e);
        }
    }


    @Override
    public void setExerciseStatus(ExerciseBean bean) {
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(event -> correctSetUp.setVisible(false));
        correctSetUp.setVisible(true);
        pause.play();
    }
    @Override
    public void update(String exerciseName, ExerciseStatus status) {
        updateLabel.setText("The exercise: " + exerciseName + " is now " + status);
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(event -> updateLabel.setVisible(false));
        updateLabel.setVisible(true);
        pause.play();
        updateExerciseList();
        for (WorkoutDayBean entry : workoutRoutine.getWorkoutDayList()) {
            List<ExerciseForWorkoutRoutineBean> exerciseList = entry.getExerciseList();

            for (ExerciseForWorkoutRoutineBean ex : exerciseList) {
                if (ex.getName().equals(exerciseName)) {
                    ex.setStatusExercise(status);
                }
            }
        }
        try {
            selectedDay = getNameDay();
            updateSelectedExerciseList();
        } catch (NoDayIsSelectedException e) {
            e.callMe(2);
        }
    }

    @Override
    public void submitWorkoutRoutine(String comment, String name) {
        try{
            SatisfyWorkoutRequestsController controller = new SatisfyWorkoutRequestsController();
            this.workoutRoutine.setComment(comment);
            this.workoutRoutine.setName(name);
            MainMenuSingleton.getMainMenu().setActivity("ptHome.fxml", "pt");
            try {
                controller.sendWorkoutRoutine(this.requestBean, this.workoutRoutine);
            } catch (DBUnrreachableException e) {
                e.callMe(2);
            }
        } catch (NoLoggedUserException | SubmitRoutineException e){
            e.callMe(2);
        } catch (IOException e){
            CostumeLogger.getInstance().logString("IOException in submitWorkoutRoutine");
        }

    }

    @FXML
    public void searchButton() {
        try {
            SatisfyWorkoutRequestsController controller = new SatisfyWorkoutRequestsController();
            List<ExerciseBean> filteredList;
            String searchText = searchExerciseText.getText();
            filteredList = controller.searchExercise(new SearchBean(searchText));
            ManageExerciseList.updateListFiltered(exerciseDBList2, filteredList);
        } catch (NoLoggedUserException | EmptySearchException e) {
            e.callMe(2);
            CostumeLogger.getInstance().logError(e);
        }
    }


    public void goForward() {
        try{
            PersonalizeWRPopUp.getPersonalizeWRPopup(this, this.workoutRoutine,"PersonalizeWRPopUp.fxml" , "popups", 2);
        } catch (IOException e) {
            CostumeLogger.getInstance().logString("IOException in goForward");
        }
    }
}