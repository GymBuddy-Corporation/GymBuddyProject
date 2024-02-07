package viewone.managelistview.listcells;

import beans.ExerciseBean;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ExerciseListCellFactoryForStatus implements Callback<ListView<ExerciseBean>, ListCell<ExerciseBean>> {

    @Override
    public ListCell<ExerciseBean> call(ListView<ExerciseBean> param) {
        //it's the same so i'll just call the other one
        return ExerciseListCellFactory.getListener();
    }
}
