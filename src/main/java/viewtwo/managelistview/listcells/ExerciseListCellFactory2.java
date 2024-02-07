package viewtwo.managelistview.listcells;

import beans.ExerciseBean;
import exceptions.logger.CostumeLogger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
            private Parent parentNode = null;



            @Override
            protected void updateItem(ExerciseBean exerciseBean, boolean empty) {
                super.updateItem(exerciseBean, empty);

                if (empty || exerciseBean == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if(parentNode==null){
                        {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewtwo/pt/ListExerciseWorkoutRoutine2.fxml"));
                                parentNode=loader.load();
                                itemNameLabel = (Label)parentNode.lookup("#itemName1");
                            } catch (IOException e) {
                                CostumeLogger.getInstance().logError(e);
                            }
                        }
                    }
                        itemNameLabel.setText(exerciseBean.getName());
                        setGraphic(itemNameLabel);

                    }
            }
        };
    }
}
