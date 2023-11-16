package engineering.list_cell_factories;


import beans.ExerciseBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.util.Callback;

import java.io.IOException;

public class ExerciseListCellFactory implements Callback<ListView<ExerciseBean>, ListCell<ExerciseBean>> {

    @Override
    public ListCell<ExerciseBean> call(ListView<ExerciseBean> param) {
        return new ListCell<ExerciseBean>() {
            private Parent parentNode = null;

            @Override
            protected void updateItem(ExerciseBean exerciseBean, boolean empty) {
                super.updateItem(exerciseBean, empty);

                if (empty || exerciseBean == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (parentNode == null) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewone/pt/ListExerciseWorkoutRoutine.fxml"));
                            parentNode = loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    Label itemNameLabel = (Label) parentNode.lookup("#itemName1");
                    itemNameLabel.setText(exerciseBean.getName());

                    setGraphic(parentNode);
                }
            }
        };
    }
}
