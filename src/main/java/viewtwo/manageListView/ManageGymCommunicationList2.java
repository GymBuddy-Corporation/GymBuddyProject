package viewtwo.manageListView;

import beans.ExerciseBean;
import beans.ExerciseForWorkoutRoutineBean;
import beans.ExerciseStatusBean;
import controllers.SatisfyWorkoutRequestsController;
import controllers.SeeCommunications;
import exceptions.dataException.DataFieldException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import viewone.graphicalControllers.pt.CreateNewWorkoutRoutineGUIController;
import viewone.graphicalControllers.pt.SetExerciseStatusGUIController;

import java.util.List;
import java.util.stream.Collectors;

public class ManageGymCommunicationList2 {

    private ManageGymCommunicationList2() {
    }

    public static void setCommunicationList(ListView<String> communicationList) {
        SeeCommunications seeCommunications = new SeeCommunications();
        List<String> comList = seeCommunications.getGymCommunications();
        communicationList.getItems().addAll(comList);
    }
}
