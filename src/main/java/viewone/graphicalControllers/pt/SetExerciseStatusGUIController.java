package viewone.graphicalControllers.pt;

import beans.ExerciseBean;
import beans.ExerciseForWorkoutRoutineBean;
import beans.RequestBean;
import beans.SearchBean;
import controllers.SatisfyWorkoutRequestsController;
import engineering.manageListView.listCells.ExerciseListCellFactoryForStatus;
import engineering.manageListView.ManageExerciseList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import utils.MainStage;
import utils.SwitchPage;
import viewone.ExerciseStatusButtonController;
import beans.ExerciseStatusBean;

import java.net.URL;
import java.util.*;

public class SetExerciseStatusGUIController implements Initializable{

    /*private Map<String, List<ExerciseForWorkoutRoutineBean>> dayExercisesMap;
    private RequestBean requestBean;*/
    private SatisfyWorkoutRequestsController satisfyWorkoutRequestsController;
    @FXML private ListView<ExerciseBean> exerciseDBList;
    @FXML private Button suspendStatusButton;
    @FXML private Button activeStatusButton;
    Button selectedButton;
    @FXML private TextField searchExerciseText;
    @FXML private Button setStatusButton;
    private final ExerciseStatusButtonController exerciseStatusButtonController = new ExerciseStatusButtonController();;
    private SatisfyWorkoutRoutineRequestGUIController satisfyWorkoutRoutineRequestGUIController;

    @FXML
    public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(), "Login.fxml", "launcher", 1);
    }
    @FXML public void searchExercise(){
        //TODO controlla se funziona
        List<ExerciseBean> exerciseBeanList = satisfyWorkoutRequestsController.searchExercise(new SearchBean(searchExerciseText.getText()));
        System.out.println("Exercise Bean List Size: " + exerciseBeanList.size());
        ManageExerciseList.updateList(exerciseDBList, exerciseBeanList);
    }

    public ListView<ExerciseBean> getExerciseDBList() {
        return exerciseDBList;
    }

    public void setVisibleButtons(Boolean bool) {
        activeStatusButton.setVisible(bool);
        suspendStatusButton.setVisible(bool);
        setStatusButton.setVisible(bool);
    }

    public void setValue(RequestBean requestBean, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, Map<String, List<ExerciseForWorkoutRoutineBean>> dayExercisesMap, SatisfyWorkoutRoutineRequestGUIController satisfyWorkoutRoutineRequestGUIController) {
        this.satisfyWorkoutRoutineRequestGUIController = satisfyWorkoutRoutineRequestGUIController;
        /*this.dayExercisesMap=dayExercisesMap;
        this.requestBean= requestBean;*/
        this.satisfyWorkoutRequestsController=satisfyWorkoutRequestsController;
        ManageExerciseList.setListenerDBSet(exerciseDBList, satisfyWorkoutRequestsController, this);
        List<ExerciseBean> exerciseBeanList = satisfyWorkoutRequestsController.getGymExerciseBean();
        ManageExerciseList.updateList(exerciseDBList, exerciseBeanList);
    }

    @FXML
    public void changeStatus(ActionEvent event) {
        selectedButton = exerciseStatusButtonController.statusButtonAction(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exerciseDBList.setCellFactory(new ExerciseListCellFactoryForStatus());
        setVisibleButtons(false);
    }

    public void setFireBotton(int i) {
        if(i==1){
            activeStatusButton.fire();
        } else {
            suspendStatusButton.fire();
        }
    }

    @FXML public void setButtonStatus() throws Exception{
        ExerciseBean selectedExercise = exerciseDBList.getSelectionModel().getSelectedItem();
        ExerciseStatusBean selectedStatus = null;
        String newStatus;
        if(selectedButton == activeStatusButton){
            selectedStatus = ExerciseStatusBean.ACTIVE;
            newStatus = "Active";
            System.out.println("Premuto active");
        } else if (selectedButton == suspendStatusButton){
            selectedStatus = ExerciseStatusBean.SUSPENDED;
            newStatus = "Suspended";
            System.out.println("Premuto suspended");
        } else {
            System.out.println("Premuto niente");
            newStatus = "";
            //TODO then throw exception
        }

        satisfyWorkoutRequestsController.setExerciseStatus(selectedExercise, selectedStatus);
        satisfyWorkoutRoutineRequestGUIController = (SatisfyWorkoutRoutineRequestGUIController) SwitchPage.setStage(MainStage.getStage(),"SatisfyWorkoutRoutineRequest.fxml","pt",1);

        //satisfyWorkoutRoutineRequestGUIController = (SatisfyWorkoutRoutineRequestGUIController) SwitchPage.getController("SatisfyWorkoutRoutineRequest.fxml","pt");
        //Objects.requireNonNull(satisfyWorkoutRoutineRequestGUIController).setBackValue(requestBean, satisfyWorkoutRequestsController, dayExercisesMap, newStatus);
    }

    /*private ExerciseStatusBean getStatusBean(ExerciseStatus status){
        if (status){
            return ACTIVE;
        }
    }*/


}