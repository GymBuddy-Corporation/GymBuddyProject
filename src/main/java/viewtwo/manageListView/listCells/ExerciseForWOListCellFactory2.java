package viewtwo.manageListView.listCells;

import beans.ExerciseForWorkoutRoutineBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;

public class ExerciseForWOListCellFactory2 implements Callback<ListView<ExerciseForWorkoutRoutineBean>, ListCell<ExerciseForWorkoutRoutineBean>> {

    @Override
    public ListCell<ExerciseForWorkoutRoutineBean> call(ListView<ExerciseForWorkoutRoutineBean> param) {
        return new ListCell<ExerciseForWorkoutRoutineBean>() {
            @Override
            protected void updateItem(ExerciseForWorkoutRoutineBean exerciseBean, boolean empty) {
                super.updateItem(exerciseBean, empty);

                if (empty || exerciseBean == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewone/pt/ListExerciseWorkoutRoutine.fxml"));
                    Label itemNameLabel = (Label) loader.getNamespace().get("itemName1");
                    itemNameLabel.setText(exerciseBean.getName());
                    setGraphic(itemNameLabel);
                }
            }
        };
    }
}
