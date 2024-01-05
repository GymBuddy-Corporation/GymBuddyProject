package viewone.graphicalControllers.pt;

import beans.ExerciseBean;
import beans.SearchBean;
import controllers.SatisfyWorkoutRequestsController;
import viewone.manageListView.listCells.ExerciseListCellFactoryForStatus;
import viewone.manageListView.ManageExerciseList;
import exceptions.UserCastException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    @FXML private ListView<ExerciseBean> exerciseDBList;
    @FXML private Button suspendStatusButton;
    @FXML private Button activeStatusButton;
    Button selectedButton;
    @FXML private TextField searchExerciseText;
    @FXML private Button setStatusButton;
    private final ExerciseStatusButtonController exerciseStatusButtonController = new ExerciseStatusButtonController();;
    private CreateNewWorkoutRoutineGUIController satisfyWorkoutRoutineRequestGUIController;

    @FXML
    public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(), "Login.fxml", "launcher", 1);
    }
    @FXML public void searchExercise() throws UserCastException{
        //TODO controlla se funziona
        SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
        List<ExerciseBean> exerciseBeanList = satisfyWorkoutRequestsController.searchExercise(new SearchBean(searchExerciseText.getText()));
        System.out.println("Exercise Bean List Size: " + exerciseBeanList.size());
        ManageExerciseList.updateListFiltered(exerciseDBList, exerciseBeanList);
    }

    public ListView<ExerciseBean> getExerciseDBList() {
        return exerciseDBList;
    }

    public void setVisibleButtons(Boolean bool) {
        activeStatusButton.setVisible(bool);
        suspendStatusButton.setVisible(bool);
        setStatusButton.setVisible(bool);
    }

    /*public void setValue(CreateNewWorkoutRoutineGUIController satisfyWorkoutRoutineRequestGUIController) throws UserCastException {
        this.satisfyWorkoutRoutineRequestGUIController = satisfyWorkoutRoutineRequestGUIController;
        SatisfyWorkoutRequestsController satisfyWorkoutRequestsController= new SatisfyWorkoutRequestsController();
        ManageExerciseList.setListenerDBSet(exerciseDBList, satisfyWorkoutRequestsController, this);
        List<ExerciseBean> exerciseBeanList = satisfyWorkoutRequestsController.getLoggedTrainerGymExercises();
        ManageExerciseList.updateList(exerciseDBList, exerciseBeanList);
    }*/

    @FXML
    public void changeStatus(ActionEvent event) {
        selectedButton = exerciseStatusButtonController.statusButtonAction(event);
    }

    public void showExerciseDBList(ListView<ExerciseBean> exerciseDBList,SatisfyWorkoutRequestsController satisfyWorkoutRequestsController){
        ObservableList<ExerciseBean> requestBeanObservableList = FXCollections.observableList(satisfyWorkoutRequestsController.getLoggedTrainerGymExercises());
        exerciseDBList.setItems(FXCollections.observableList(requestBeanObservableList));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exerciseDBList.setCellFactory(new ExerciseListCellFactoryForStatus());
        SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
        showExerciseDBList(exerciseDBList, satisfyWorkoutRequestsController);
        ManageExerciseList.setListenerDBSet(exerciseDBList, satisfyWorkoutRequestsController, this);
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

        if(selectedButton == activeStatusButton){
            selectedStatus = ExerciseStatusBean.ACTIVE;
            System.out.println("Premuto active");

        } else if (selectedButton == suspendStatusButton){
            selectedStatus = ExerciseStatusBean.SUSPENDED;
            System.out.println("Premuto suspended");

        } else {
            System.out.println("Premuto niente");
            //TODO then throw exception
        }

        SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
        satisfyWorkoutRequestsController.setExerciseStatus(selectedExercise, selectedStatus);
        satisfyWorkoutRoutineRequestGUIController = (CreateNewWorkoutRoutineGUIController) SwitchPage.getController("CreateNewWorkoutRoutine.fxml","pt");


        SwitchPage.setStage(MainStage.getStage(),"CreateNewWorkoutRoutine.fxml","pt",1);
    }

    /*private ExerciseStatusBean getStatusBean(ExerciseStatus status){
        if (status){
            return ACTIVE;
        }
    }*/


}