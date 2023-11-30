package engineering.manageListView.listCells;


import beans.AthleteBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;

public class AthleteListCellFactory implements Callback<ListView<AthleteBean>, ListCell<AthleteBean>> {

    @Override
    public ListCell<AthleteBean> call(ListView<AthleteBean> param) {
        return new ListCell<AthleteBean>() {
            private Parent parentNode = null;

            @Override
            protected void updateItem(AthleteBean requestBean, boolean empty) {
                super.updateItem(requestBean, empty);

                if (empty || requestBean == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (parentNode == null) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewone/pt/ListAthlete.fxml"));
                            parentNode = loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    Label itemNameLabel = (Label) parentNode.lookup("#itemName1");
                    itemNameLabel.setText(requestBean.getUsername());

                    setGraphic(parentNode);
                }
            }
        };
    }
}
