package viewone.graphicalcontrollers.pt;

import beans.ExerciseBean;
import beans.SearchBean;
import controllers.SatisfyWorkoutRequestsController;
import exceptions.NoLoggedUserException;
import exceptions.UserCastException;
import exceptions.logger.CostumeLogger;
import model.ExerciseStatus;
import viewone.managelistview.listcells.ExerciseListCellFactoryForStatus;
import viewone.managelistview.ManageExerciseList;
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

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SetExerciseStatusGUIController implements Initializable{

    @FXML private ListView<ExerciseBean> exerciseDBList;
    @FXML private Button suspendStatusButton;
    @FXML private Button activeStatusButton;
    Button selectedButton;
    @FXML private TextField searchExerciseText;
    @FXML private Button setStatusButton;
    private final ExerciseStatusButtonController exerciseStatusButtonController = new ExerciseStatusButtonController();

    @FXML
    public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(), "Login.fxml", "launcher", 1);
    }
    @FXML public void searchExercise(){
        //TODO controlla se funziona
        SatisfyWorkoutRequestsController controller;
        try{
            controller = new SatisfyWorkoutRequestsController();
        } catch (NoLoggedUserException e){
                e.callMe(1);
                return;
        }
        List<ExerciseBean> exerciseBeanList = controller.searchExercise(new SearchBean(searchExerciseText.getText()));
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
        SatisfyWorkoutRequestsController controller;
        try{
            controller = new SatisfyWorkoutRequestsController();
            showExerciseDBList(exerciseDBList, controller);
            ManageExerciseList.setListenerDBSet(exerciseDBList, controller, this);
            setVisibleButtons(false);
        } catch (NoLoggedUserException e){

                e.callMe(1);

        }
    }

    public void setFireBotton(int i) {
        if(i==1){
            activeStatusButton.fire();
        } else {
            suspendStatusButton.fire();
        }
    }

    @FXML public void setButtonStatus() {
        ExerciseBean selectedExercise = exerciseDBList.getSelectionModel().getSelectedItem();
        ExerciseStatus selectedStatus = null;

        if(selectedButton == activeStatusButton){
            selectedStatus = ExerciseStatus.ACTIVE;

        } else if (selectedButton == suspendStatusButton){
            selectedStatus = ExerciseStatus.SUSPENDED;

        } else {
            //TODO then throw exception
        }

        SatisfyWorkoutRequestsController controller;
        try{
            controller = new SatisfyWorkoutRequestsController();
            controller.setExerciseStatus(selectedExercise, selectedStatus);
            SwitchPage.getController("CreateNewWorkoutRoutine.fxml", "pt");
            SwitchPage.setStage(MainStage.getStage(), "CreateNewWorkoutRoutine.fxml", "pt", 1);
        } catch (NoLoggedUserException e){
                e.callMe(1);
        } catch ( IOException e){
            CostumeLogger.getInstance().logError(e);
        }

    }
}