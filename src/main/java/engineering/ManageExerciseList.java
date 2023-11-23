package engineering;

import beans.ExerciseBean;
import beans.ExerciseForWorkoutRoutineBean;
import controllers.SatisfyWorkoutRequestsController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import viewone.DaysOfTheWeekButtonController;
import viewone.graphical_controllers.pt.SatisfyWorkoutRoutineRequestGUIController;


import java.util.List;

public class ManageExerciseList {

    private ManageExerciseList() {}

    public static void setListenerDB(ListView<ExerciseBean> exerciseList, DaysOfTheWeekButtonController daysController, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, SatisfyWorkoutRoutineRequestGUIController satisfyWorkoutRoutineRequestGUIController) {
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


    public static void setListenerRoutineWorkout(ListView<ExerciseForWorkoutRoutineBean> exerciseList, DaysOfTheWeekButtonController daysController, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, SatisfyWorkoutRoutineRequestGUIController satisfyWorkoutRoutineRequestGUIController) {
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
            //implementa delle get per prendere i valori
            satisfyWorkoutRoutineRequestGUIController.setExerciseDetails(newItem.getRepetitions(), newItem.getSets(), newItem.getRest());
            satisfyWorkoutRoutineRequestGUIController.setVisibleLabel(true);
            satisfyWorkoutRoutineRequestGUIController.setVisibleAdd(false);
            satisfyWorkoutRoutineRequestGUIController.setVisibleCancel(true);
            satisfyWorkoutRoutineRequestGUIController.resetSelection(2);
        } else {
            satisfyWorkoutRoutineRequestGUIController.setVisibleLabel(false);
            satisfyWorkoutRoutineRequestGUIController.setVisibleCancel(false);
        }
    }

}
