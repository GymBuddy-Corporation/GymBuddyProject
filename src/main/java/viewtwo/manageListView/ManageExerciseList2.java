package viewtwo.manageListView;

import beans.ExerciseBean;
import beans.ExerciseForWorkoutRoutineBean;
import beans.ExerciseStatusBean;
import controllers.SatisfyWorkoutRequestsController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import viewone.graphicalControllers.pt.CreateNewWorkoutRoutineGUIController;
import viewone.graphicalControllers.pt.SetExerciseStatusGUIController;

import java.util.List;
import java.util.stream.Collectors;

public class ManageExerciseList2 {

    private ManageExerciseList2() {
    }

    public static void setListenerDB(ListView<ExerciseBean> exerciseList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, CreateNewWorkoutRoutineGUIController satisfyWorkoutRoutineRequestGUIController) {
        exerciseList.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends ExerciseBean> observableValue, ExerciseBean oldItem, ExerciseBean newItem) -> listEventDB(newItem, satisfyWorkoutRoutineRequestGUIController)
        );
    }

    private static void listEventDB(ExerciseBean newItem, CreateNewWorkoutRoutineGUIController satisfyWorkoutRoutineRequestGUIController) {
        if (newItem != null) {
            satisfyWorkoutRoutineRequestGUIController.setVisibleLabel(true);
            satisfyWorkoutRoutineRequestGUIController.setVisibleAdd(true);
            satisfyWorkoutRoutineRequestGUIController.setVisibleCancel(false);
            satisfyWorkoutRoutineRequestGUIController.resetInputCollectors();
            satisfyWorkoutRoutineRequestGUIController.resetSelection(1);
        }
    }

    public static void updateListFiltered(ListView<ExerciseBean> exerciseBeanListView, List<ExerciseBean> exerciseBeanList) {
        ObservableList<ExerciseBean> filteredList = exerciseBeanList.stream()
                .filter(exerciseBean -> ExerciseStatusBean.ACTIVE.equals(exerciseBean.getStatusExercise()))
                .collect(Collectors.collectingAndThen(Collectors.toList(), FXCollections::observableList));

        exerciseBeanListView.setItems(filteredList);
    }

    public static void updateListFilteredDB(ListView<ExerciseForWorkoutRoutineBean> exerciseBeanListView, List<ExerciseBean> exerciseBeanList) {
        ObservableList<ExerciseForWorkoutRoutineBean> filteredList = exerciseBeanListView.getItems().stream()
                .filter(exerciseForWorkoutRoutineBean -> exerciseForWorkoutRoutineBean != null &&
                        exerciseBeanList.contains(exerciseForWorkoutRoutineBean) &&
                        ExerciseStatusBean.ACTIVE.equals(exerciseForWorkoutRoutineBean.getStatusExercise()))
                .collect(Collectors.collectingAndThen(Collectors.toList(), FXCollections::observableList));

        exerciseBeanListView.setItems(filteredList);
    }

    public static void setListenerRoutineWorkout(ListView<ExerciseForWorkoutRoutineBean> exerciseList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, CreateNewWorkoutRoutineGUIController satisfyWorkoutRoutineRequestGUIController) {
        exerciseList.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends ExerciseForWorkoutRoutineBean> observableValue, ExerciseForWorkoutRoutineBean oldItem, ExerciseForWorkoutRoutineBean newItem) -> listEventRoutineWorkout(newItem, satisfyWorkoutRoutineRequestGUIController)
        );
    }

    private static void listEventRoutineWorkout(ExerciseForWorkoutRoutineBean newItem, CreateNewWorkoutRoutineGUIController satisfyWorkoutRoutineRequestGUIController) {
        if (newItem != null) {
            satisfyWorkoutRoutineRequestGUIController.setExerciseDetails(newItem.getRepetitions(), newItem.getSets(), newItem.getRest());
            satisfyWorkoutRoutineRequestGUIController.setVisibleLabel(true);
            satisfyWorkoutRoutineRequestGUIController.setVisibleAdd(false);
            satisfyWorkoutRoutineRequestGUIController.setVisibleCancel(true);
            satisfyWorkoutRoutineRequestGUIController.resetSelection(2);
        }
    }

    public static void setListenerDBSet(ListView<ExerciseBean> exerciseDBList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, SetExerciseStatusGUIController setExerciseStatusGUIController) {
        exerciseDBList.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends ExerciseBean> observableValue, ExerciseBean oldItem, ExerciseBean newItem) -> listEventDBSet(newItem, setExerciseStatusGUIController)
        );
    }

    private static void listEventDBSet(ExerciseBean newItem, SetExerciseStatusGUIController setExerciseStatusGUIController) {
        if (newItem != null) {
            setExerciseStatusGUIController.setVisibleButtons(true);
            ExerciseBean selectedExercise = setExerciseStatusGUIController.getExerciseDBList().getSelectionModel().getSelectedItem();
            setExerciseStatusGUIController.setFireBotton(selectedExercise.getStatusExercise() == ExerciseStatusBean.ACTIVE ? 1 : 2);
        }
    }
}
