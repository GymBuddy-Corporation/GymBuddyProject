package viewtwo.managelistview.listCells;

import beans.ExerciseBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;

public class ExerciseListCellFactoryForStatus2 implements Callback<ListView<ExerciseBean>, ListCell<ExerciseBean>> {

    @Override
    public ListCell<ExerciseBean> call(ListView<ExerciseBean> param) {
        return new ExerciseListCell();
    }

    private static class ExerciseListCell extends ListCell<ExerciseBean> {
        private static final String FXML_PATH = "/viewone/pt/ListExerciseWorkoutRoutine.fxml";
        private final Label itemNameLabel;

        public ExerciseListCell() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH));
                Parent parentNode = loader.load();
                itemNameLabel = (Label) parentNode.lookup("#itemName1");
                setGraphic(parentNode);
            } catch (IOException e) {
                throw new RuntimeException("Error loading FXML", e);
            }
        }

        @Override
        protected void updateItem(ExerciseBean exerciseBean, boolean empty) {
            super.updateItem(exerciseBean, empty);

            if (empty || exerciseBean == null) {
                setText(null);
            } else {
                itemNameLabel.setText(exerciseBean.getName());
            }
        }
    }
}
