package viewtwo.manageListView;

import beans.ExerciseBean;
import beans.ExerciseForWorkoutRoutineBean;
import exceptions.logger.CostumeLogger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import model.ExerciseStatus;
import viewone.graphicalcontrollers.pt.SetExerciseStatusGUIController;
import viewtwo.graphicalcontrollers.pt.CreateNewWorkoutRoutineGUIController2;
import viewtwo.popups.AddExePopUp;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ManageExerciseList2 {

    private ManageExerciseList2() {
    }

    public static void setListenerDB(ListView<ExerciseBean> exerciseList, CreateNewWorkoutRoutineGUIController2 controller) {
        exerciseList.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends ExerciseBean> observableValue, ExerciseBean oldItem, ExerciseBean newItem) ->
                        listEventDB(newItem, controller)
        );
    }

    private static void listEventDB(ExerciseBean newItem, CreateNewWorkoutRoutineGUIController2 controller) {
        if (newItem != null) {
            try{
                String day = controller.getSelectedDay();
                System.out.println(newItem.getName() + " giorno: " + day);
                AddExePopUp.getAddExePopup(controller, newItem.getName(), day, "AddExercisePopUp.fxml", "popups", 2);
            } catch (IOException e){
                CostumeLogger.getInstance().logError(e);
            }
            /*todo togli sti schifi e gestisci la merda di queste funzioni fxml sotto
                controller.setVisibleLabel(true);
                controller.setVisibleAdd(true);
                controller.setVisibleCancel(false);*/
            controller.resetSelection(1);
        }
    }

    public static void updateListFiltered(ListView<ExerciseBean> exerciseListView, List<ExerciseBean> exerciseList) {
        List<ExerciseBean> filteredList = exerciseList.stream()
                .filter(exerciseBean -> ExerciseStatus.ACTIVE.equals(exerciseBean.getStatusExercise()))
                .collect(Collectors.toList());

        ObservableList<ExerciseBean> filteredObservableList = FXCollections.observableList(filteredList);
        exerciseListView.setItems(filteredObservableList);
    }

    public static void updateListFilteredDB(ListView<ExerciseForWorkoutRoutineBean> exerciseListView, List<ExerciseBean> exerciseList) {
        List<ExerciseForWorkoutRoutineBean> filteredList = exerciseListView.getItems().stream()
                .filter(exerciseForWorkoutRoutineBean ->
                        exerciseForWorkoutRoutineBean != null &&
                                exerciseList.contains(exerciseForWorkoutRoutineBean) &&
                                ExerciseStatus.ACTIVE.equals(exerciseForWorkoutRoutineBean.getStatusExercise()))
                .collect(Collectors.toList());

        ObservableList<ExerciseForWorkoutRoutineBean> filteredObservableList = FXCollections.observableList(filteredList);
        exerciseListView.setItems(filteredObservableList);
    }

    public static void setListenerRoutineWorkout(ListView<ExerciseForWorkoutRoutineBean> exerciseList, CreateNewWorkoutRoutineGUIController2 controller) {
        exerciseList.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends ExerciseForWorkoutRoutineBean> observableValue, ExerciseForWorkoutRoutineBean oldItem, ExerciseForWorkoutRoutineBean newItem) ->
                        listEventRoutineWorkout(newItem, controller)
        );
    }

    private static void listEventRoutineWorkout(ExerciseForWorkoutRoutineBean newItem, CreateNewWorkoutRoutineGUIController2 controller) {
        if (newItem != null) {
            //controller.setExerciseDetails(newItem.getRepetitions(), newItem.getSets(), newItem.getRest());
            controller.setVisibleLabel(true);
            controller.setVisibleAdd(false);
            controller.setVisibleCancel(true);
            controller.resetSelection(2);
        }
    }

    public static void setListenerDBSet(ListView<ExerciseBean> exerciseDBList, SetExerciseStatusGUIController setController) {
        exerciseDBList.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends ExerciseBean> observableValue, ExerciseBean oldItem, ExerciseBean newItem) ->
                        listEventDBSet(newItem, setController)
        );
    }

    private static void listEventDBSet(ExerciseBean newItem, SetExerciseStatusGUIController setController) {
        if (newItem != null) {
            setController.setVisibleButtons(true);
            ExerciseBean selectedExercise = setController.getExerciseDBList().getSelectionModel().getSelectedItem();
            if (selectedExercise.getStatusExercise() == ExerciseStatus.ACTIVE) {
                //.setFireButton(1);
            } else {
                //setController.setFireButton(2);
            }
        }
    }
}
