package viewtwo.popups.controllers;

import beans.ExerciseBean;
import beans.SearchBean;
import controllers.SatisfyWorkoutRequestsController;
import engineering.popups.PopupBaseClass;
import engineering.popups.PopupBaseController;
import exceptions.CostumException;
import exceptions.EmptySearchException;
import exceptions.NoLoggedUserException;
import exceptions.UserCastException;
import exceptions.logger.CostumeLogger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import model.ExerciseStatus;
import viewone.managelistview.ManageExerciseList;
import viewtwo.manageListView.ManageExerciseList2;
import viewtwo.manageListView.listCells.ExerciseListCellFactory2;
import viewtwo.popups.ChangeExeStatusPopUp;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class ChangeExerciseStatusPopUpController extends PopupBaseController implements Initializable {
    @FXML
    ListView<ExerciseBean> exerciseDBList2;
    @FXML RadioButton activeRadioButton;
    @FXML RadioButton suspendedRadioButton;
    @FXML HBox hboxButtons;
    @FXML TextField searchTextField;
    List<RadioButton> radioButtonList;
    ChangeExeStatusPopUp caller;

    private void showExerciseDBList(){
        try {
            SatisfyWorkoutRequestsController controller = new SatisfyWorkoutRequestsController();
            ObservableList<ExerciseBean> requestBeanObservableList = FXCollections.observableList(controller.getLoggedTrainerGymExercises());
            exerciseDBList2.setItems(FXCollections.observableList(requestBeanObservableList));
        }catch (CostumException e){
                e.callMe(2);
                caller.hidePopUp();
        }
    }

    public void setVisibilityButtons(boolean bool){
        suspendedRadioButton.setVisible(bool);
        activeRadioButton.setVisible(bool);
        hboxButtons.setVisible(bool);
    }

    @Override
    public void setCaller(PopupBaseClass caller) {
        this.caller=(ChangeExeStatusPopUp) caller;
    }

    @FXML public void changeStatus(){
        try{
            SatisfyWorkoutRequestsController controller = new SatisfyWorkoutRequestsController();
            if (activeRadioButton.isSelected()){
                ExerciseBean exe = new ExerciseBean(exerciseDBList2.getSelectionModel().getSelectedItem().getName(),
                        ExerciseStatus.ACTIVE);
                caller.setExeStatus(exe);
                controller.setExerciseStatus(exe, ExerciseStatus.ACTIVE);
            }else{
                ExerciseBean exe = new ExerciseBean(exerciseDBList2.getSelectionModel().getSelectedItem().getName(),
                        ExerciseStatus.SUSPENDED);
                caller.setExeStatus(exe);
                controller.setExerciseStatus(exe, ExerciseStatus.SUSPENDED);
            }
        } catch (NoLoggedUserException e) {
            caller.hidePopUp();
            CostumeLogger.getInstance().logError(e);
        }
    }
    @FXML public void search(){
        try{
            SatisfyWorkoutRequestsController controller = new SatisfyWorkoutRequestsController();
            List<ExerciseBean> exerciseBeanList = controller.searchExercise(new SearchBean(searchTextField.getText()));
            ManageExerciseList.updateListFiltered(exerciseDBList2, exerciseBeanList);
        } catch (NoLoggedUserException | EmptySearchException e){
            e.callMe(2);
            CostumeLogger.getInstance().logError(e);
        }
    }

    public void setFireButton(int i){
        if(i == 1){
            activeRadioButton.fire();
        } else {
            suspendedRadioButton.fire();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exerciseDBList2.setCellFactory(new ExerciseListCellFactory2());
        showExerciseDBList();
        ManageExerciseList2.setListenerDBSet(exerciseDBList2, this );
        setVisibilityButtons(false);
        radioButtonList = Arrays.asList(
                activeRadioButton,
                suspendedRadioButton
        );
        ToggleGroup group = new ToggleGroup();
        activeRadioButton.setToggleGroup(group);
        suspendedRadioButton.setToggleGroup(group);
    }

    public ListView<ExerciseBean> getExerciseDBList() {
        return exerciseDBList2;
    }
}