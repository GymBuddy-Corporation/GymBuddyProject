package viewone.managelistview;

import beans.*;
import controllers.SatisfyWorkoutRequestsController;
import controllers.SeeCommunications;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import model.ExerciseStatus;
import viewone.graphicalcontrollers.pt.CreateNewWorkoutRoutineGUIController;
import viewone.graphicalcontrollers.pt.SetExerciseStatusGUIController;

import java.util.List;
import java.util.stream.Collectors;

public class ManageGymCommunicationList {

    private ManageGymCommunicationList() {}

    public static void setListenerDB(ListView<ExerciseBean> exerciseList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, CreateNewWorkoutRoutineGUIController satisfyWorkoutRoutineRequestGUIController) {
        exerciseList.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends ExerciseBean> observableValue, ExerciseBean oldItem, ExerciseBean newItem) {
                        listEventDB(newItem, satisfyWorkoutRoutineRequestGUIController);
                    }
                });
    }

    private static void listEventDB(ExerciseBean newItem, CreateNewWorkoutRoutineGUIController satisfyWorkoutRoutineRequestGUIController) {
            if(newItem != null){
                satisfyWorkoutRoutineRequestGUIController.setVisibleLabel(true);
                satisfyWorkoutRoutineRequestGUIController.setVisibleAdd(true);
                satisfyWorkoutRoutineRequestGUIController.setVisibleCancel(false);
                satisfyWorkoutRoutineRequestGUIController.resetInputCollectors();
                satisfyWorkoutRoutineRequestGUIController.resetSelection(1);
            }
    }

    public static void setCommunicationList(ListView<String> communicationList) {
        SeeCommunications seeCommunications = new SeeCommunications();
        List<String> comList;
        comList = seeCommunications.getGymCommunications();
        communicationList.getItems().addAll();
    }

    public static void updateListFiltered(ListView<ExerciseBean> exerciseBeanListView, List<ExerciseBean> exerciseBeanList) {
        // Filter out exercises with status other than ACTIVE
        List<ExerciseBean> filteredList = exerciseBeanList.stream()
                .filter(exerciseBean -> ExerciseStatus.ACTIVE.equals(exerciseBean.getStatusExercise()))
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
                            ExerciseStatus.ACTIVE.equals(exerciseForWorkoutRoutineBean.getStatusExercise());
                })
                .collect(Collectors.toList());

        ObservableList<ExerciseForWorkoutRoutineBean> exerciseForWorkoutRoutineObservableList = FXCollections.observableList(filteredList);
        exerciseBeanListView.setItems(exerciseForWorkoutRoutineObservableList);
    }


    public static void setListenerRoutineWorkout(ListView<ExerciseForWorkoutRoutineBean> exerciseList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, CreateNewWorkoutRoutineGUIController satisfyWorkoutRoutineRequestGUIController) {
        exerciseList.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends ExerciseForWorkoutRoutineBean> observableValue, ExerciseForWorkoutRoutineBean oldItem, ExerciseForWorkoutRoutineBean newItem) {
                        listEventRoutineWorkout(newItem, satisfyWorkoutRoutineRequestGUIController);
                    }
                });
    }

    private static void listEventRoutineWorkout(ExerciseForWorkoutRoutineBean newItem, CreateNewWorkoutRoutineGUIController satisfyWorkoutRoutineRequestGUIController) {
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
            if(selectedExercise.getStatusExercise() == ExerciseStatus.ACTIVE){
                setExerciseStatusGUIController.setFireBotton(1);
            } else {
                setExerciseStatusGUIController.setFireBotton(2);
            }
        }
    }
}
