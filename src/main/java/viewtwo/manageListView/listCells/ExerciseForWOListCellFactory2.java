package viewtwo.manageListView.listCells;

import beans.ExerciseForWorkoutRoutineBean;
import exceptions.logger.CostumeLogger;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;

public class ExerciseForWOListCellFactory2 implements Callback<ListView<ExerciseForWorkoutRoutineBean>, ListCell<ExerciseForWorkoutRoutineBean>> {

    @Override public ListCell<ExerciseForWorkoutRoutineBean> call(ListView<ExerciseForWorkoutRoutineBean> param) {
        return new ListCell<ExerciseForWorkoutRoutineBean>() {
            private Label itemNameLabel;

            {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewtwo/pt/ListExerciseWorkoutRoutine2.fxml"));
                    loader.load();
                    itemNameLabel = (Label) loader.getNamespace().get("itemName1");
                } catch (IOException e) {
                    CostumeLogger.getInstance().logError(e);
                }
            }
            @Override
            protected void updateItem(ExerciseForWorkoutRoutineBean exerciseBean, boolean empty) {
                super.updateItem(exerciseBean, empty);

                if (empty || exerciseBean == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    itemNameLabel.setText(exerciseBean.getName());
                    setGraphic(itemNameLabel);
                }
            }
        };
    }
}
