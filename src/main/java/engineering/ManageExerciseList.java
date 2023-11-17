package engineering;

import beans.ExerciseBean;
import beans.ExerciseForWorkoutRoutineBean;
import controllers.SatisfyWorkoutRequestsController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import viewone.DaysOfTheWeekButtonController;
import viewone.graphical_controllers.pt.SatisfyWorkoutRoutineRequestGUIController;


import java.io.IOException;
import java.util.List;

public class ManageExerciseList {

    private ManageExerciseList() {}

    public static void setListenerDB(ListView<ExerciseBean> exerciseList, DaysOfTheWeekButtonController daysController, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, SatisfyWorkoutRoutineRequestGUIController satisfyWorkoutRoutineRequestGUIController) {
        exerciseList.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends ExerciseBean> observableValue, ExerciseBean oldItem, ExerciseBean newItem) {
                        listEventDB(newItem, daysController, exerciseList, satisfyWorkoutRequestsController, satisfyWorkoutRoutineRequestGUIController);
                    }
                });
    }

    private static void listEventDB(ExerciseBean newItem, DaysOfTheWeekButtonController daysController, ListView<ExerciseBean> exerciseList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, SatisfyWorkoutRoutineRequestGUIController satisfyWorkoutRoutineRequestGUIController) {
            if(newItem != null){
                satisfyWorkoutRoutineRequestGUIController.setVisibleLabel(true);
                satisfyWorkoutRoutineRequestGUIController.setVisibleAdd(true);
                satisfyWorkoutRoutineRequestGUIController.setVisibleCancel(false);
                satisfyWorkoutRoutineRequestGUIController.resetInputCollectors();
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
                        listEventRoutineWorkout(newItem, daysController, exerciseList, satisfyWorkoutRequestsController, satisfyWorkoutRoutineRequestGUIController);
                    }
                });
    }

    private static void listEventRoutineWorkout(ExerciseForWorkoutRoutineBean newItem, DaysOfTheWeekButtonController daysController, ListView<ExerciseForWorkoutRoutineBean> exerciseList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, SatisfyWorkoutRoutineRequestGUIController satisfyWorkoutRoutineRequestGUIController) {
        if(newItem != null){
            satisfyWorkoutRoutineRequestGUIController.setVisibleLabel(true);
            satisfyWorkoutRoutineRequestGUIController.setVisibleAdd(false);
            satisfyWorkoutRoutineRequestGUIController.setVisibleCancel(true);
        }
    }

}
