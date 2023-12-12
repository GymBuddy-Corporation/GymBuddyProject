package engineering.manageListView;

import beans.ExerciseBean;
import beans.ExerciseForWorkoutRoutineBean;
import beans.ExerciseStatusBean;
import controllers.SatisfyWorkoutRequestsController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import viewone.graphicalControllers.pt.SatisfyWorkoutRoutineRequestGUIController;
import viewone.graphicalControllers.pt.SetExerciseStatusGUIController;


import java.util.List;
import java.util.stream.Collectors;

public class ManageExerciseList {

    private ManageExerciseList() {}

    public static void setListenerDB(ListView<ExerciseBean> exerciseList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, SatisfyWorkoutRoutineRequestGUIController satisfyWorkoutRoutineRequestGUIController) {
        exerciseList.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends ExerciseBean> observableValue, ExerciseBean oldItem, ExerciseBean newItem) {
                        listEventDB(newItem, satisfyWorkoutRoutineRequestGUIController);
                    }
                });
    }

    private static void listEventDB(ExerciseBean newItem, SatisfyWorkoutRoutineRequestGUIController satisfyWorkoutRoutineRequestGUIController) {
            if(newItem != null){
                satisfyWorkoutRoutineRequestGUIController.setVisibleLabel(true);
                satisfyWorkoutRoutineRequestGUIController.setVisibleAdd(true);
                satisfyWorkoutRoutineRequestGUIController.setVisibleCancel(false);
                satisfyWorkoutRoutineRequestGUIController.resetInputCollectors();
                satisfyWorkoutRoutineRequestGUIController.resetSelection(1);
            }
    }

    public static void updateList(ListView<ExerciseBean> exerciseBeanListView, List<ExerciseBean> exerciseBeanList) {
        ObservableList<ExerciseBean> exerciseBeanObservableList = FXCollections.observableList(exerciseBeanList);
        exerciseBeanListView.setItems(exerciseBeanObservableList);
    }

    public static void updateRoutineList(ListView<ExerciseForWorkoutRoutineBean> exerciseBeanListView, List<ExerciseForWorkoutRoutineBean> exerciseBeanList) {
        ObservableList<ExerciseForWorkoutRoutineBean> exerciseBeanObservableList = FXCollections.observableList(exerciseBeanList);
        exerciseBeanListView.setItems(exerciseBeanObservableList);
    }

    public static void updateListFiltered(ListView<ExerciseBean> exerciseBeanListView, List<ExerciseBean> exerciseBeanList) {
        // Filter out exercises with status other than ACTIVE
        List<ExerciseBean> filteredList = exerciseBeanList.stream()
                .filter(exerciseBean -> ExerciseStatusBean.ACTIVE.equals(exerciseBean.getStatusExercise()))
                .collect(Collectors.toList());

        ObservableList<ExerciseBean> exerciseBeanObservableList = FXCollections.observableList(filteredList);
        exerciseBeanListView.setItems(exerciseBeanObservableList);
    }


    public static void updateListFilteredDB(ListView<ExerciseForWorkoutRoutineBean> exerciseBeanListView, List<ExerciseBean> exerciseBeanList) {
        // Filter out ExerciseForWorkoutRoutineBean with associated ExerciseBean having status other than ACTIVE
        List<ExerciseForWorkoutRoutineBean> filteredList = exerciseBeanListView.getItems().stream()
                .filter(exerciseForWorkoutRoutineBean -> {
                    return exerciseForWorkoutRoutineBean != null &&
                            exerciseBeanList.contains(exerciseForWorkoutRoutineBean) &&
                            ExerciseStatusBean.ACTIVE.equals(exerciseForWorkoutRoutineBean.getStatusExercise());
                })
                .collect(Collectors.toList());

        ObservableList<ExerciseForWorkoutRoutineBean> exerciseForWorkoutRoutineObservableList = FXCollections.observableList(filteredList);
        exerciseBeanListView.setItems(exerciseForWorkoutRoutineObservableList);
    }


    public static void setListenerRoutineWorkout(ListView<ExerciseForWorkoutRoutineBean> exerciseList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, SatisfyWorkoutRoutineRequestGUIController satisfyWorkoutRoutineRequestGUIController) {
        exerciseList.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends ExerciseForWorkoutRoutineBean> observableValue, ExerciseForWorkoutRoutineBean oldItem, ExerciseForWorkoutRoutineBean newItem) {
                        listEventRoutineWorkout(newItem, satisfyWorkoutRoutineRequestGUIController);
                    }
                });
    }

    private static void listEventRoutineWorkout(ExerciseForWorkoutRoutineBean newItem, SatisfyWorkoutRoutineRequestGUIController satisfyWorkoutRoutineRequestGUIController) {
        if(newItem != null){
            satisfyWorkoutRoutineRequestGUIController.setExerciseDetails(newItem.getRepetitions(), newItem.getSets(), newItem.getRest());
            satisfyWorkoutRoutineRequestGUIController.setVisibleLabel(true);
            satisfyWorkoutRoutineRequestGUIController.setVisibleAdd(false);
            satisfyWorkoutRoutineRequestGUIController.setVisibleCancel(true);
            satisfyWorkoutRoutineRequestGUIController.resetSelection(2);
        }
    }

    public static void setListenerDBSet(ListView<ExerciseBean> exerciseDBList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, SetExerciseStatusGUIController setExerciseStatusGUIController) {
        exerciseDBList.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends ExerciseBean> observableValue, ExerciseBean oldItem, ExerciseBean newItem) {
                        listEventDBSet(newItem, setExerciseStatusGUIController);
                    }
                });
    }

    private static void listEventDBSet(ExerciseBean newItem, SetExerciseStatusGUIController setExerciseStatusGUIController) {
        if(newItem != null){
            setExerciseStatusGUIController.setVisibleButtons(true);
            ExerciseBean selectedExercise = setExerciseStatusGUIController.getExerciseDBList().getSelectionModel().getSelectedItem();
            if(selectedExercise.getStatusExercise() == ExerciseStatusBean.ACTIVE){
                setExerciseStatusGUIController.setFireBotton(1);
            } else {
                setExerciseStatusGUIController.setFireBotton(2);
            }
        }
    }
}
