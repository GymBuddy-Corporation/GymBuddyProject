package viewtwo.manageListView.listCells;

import beans.ExerciseBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;

public class ExerciseListCellFactory2 implements Callback<ListView<ExerciseBean>, ListCell<ExerciseBean>> {

    @Override
    public ListCell<ExerciseBean> call(ListView<ExerciseBean> param) {
        return new ListCell<ExerciseBean>() {
            private Label itemNameLabel;

            {
                try {
                    // TODO: Adjust the path based on your project structure
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewone/pt/ListExerciseWorkoutRoutine.fxml"));
                    loader.load();
                    itemNameLabel = (Label) loader.getNamespace().get("itemName1");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void updateItem(ExerciseBean exerciseBean, boolean empty) {
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
