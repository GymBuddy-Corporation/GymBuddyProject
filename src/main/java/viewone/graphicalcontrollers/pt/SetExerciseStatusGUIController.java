package viewone.graphicalcontrollers.pt;

import beans.ExerciseBean;
import beans.SearchBean;
import controllers.SatisfyWorkoutRequestsController;
import controllers.UserAccessController;
import exceptions.EmptySearchException;
import exceptions.NoLoggedUserException;
import exceptions.logger.CostumeLogger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.ExerciseStatus;
import utils.MainStage;
import utils.SwitchPage;
import viewone.ExerciseStatusButtonController;
import viewone.managelistview.ManageExerciseList;
import viewone.managelistview.listcells.ExerciseListCellFactoryForStatus;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SetExerciseStatusGUIController implements Initializable{

    private final ExerciseStatusButtonController exerciseStatusButtonController = new ExerciseStatusButtonController();
    Button selectedButton;
    @FXML private ListView<ExerciseBean> exerciseDBList;
    @FXML private Button suspendStatusButton;
    @FXML private Button activeStatusButton;
    @FXML private TextField searchExerciseText;
    @FXML private Button setStatusButton;
    @FXML private Text usernameText;

    @FXML
    public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(), "Login.fxml", "launcher", 1);
    }
    @FXML public void searchExercise(){
        try{
            SatisfyWorkoutRequestsController controller = new SatisfyWorkoutRequestsController();
            List<ExerciseBean> exerciseBeanList = controller.searchExercise(new SearchBean(searchExerciseText.getText()));
            ManageExerciseList.updateListFiltered(exerciseDBList, exerciseBeanList);
        } catch (NoLoggedUserException | EmptySearchException e){
                e.callMe(1);
                CostumeLogger.getInstance().logError(e);
        }
    }

    public ListView<ExerciseBean> getExerciseDBList() {
        return exerciseDBList;
    }

    @FXML
    public void changeStatus(ActionEvent event) {
        selectedButton = exerciseStatusButtonController.statusButtonAction(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exerciseDBList.setCellFactory(new ExerciseListCellFactoryForStatus());
        SatisfyWorkoutRequestsController controller;
        try{
            controller = new SatisfyWorkoutRequestsController();
            showExerciseDBList(exerciseDBList, controller);
            ManageExerciseList.setListenerDBSet(exerciseDBList, this);
            setVisibleButtons(false);
        } catch (NoLoggedUserException e){
                e.callMe(1);
        }
        usernameText.setText(new UserAccessController().getUser().getUsername());
    }

    public void showExerciseDBList(ListView<ExerciseBean> exerciseDBList,SatisfyWorkoutRequestsController satisfyWorkoutRequestsController){
        ObservableList<ExerciseBean> requestBeanObservableList = FXCollections.observableList(satisfyWorkoutRequestsController.getLoggedTrainerGymExercises());
        exerciseDBList.setItems(FXCollections.observableList(requestBeanObservableList));
    }

    public void setVisibleButtons(Boolean bool) {
        activeStatusButton.setVisible(bool);
        suspendStatusButton.setVisible(bool);
        setStatusButton.setVisible(bool);
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
                return;
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