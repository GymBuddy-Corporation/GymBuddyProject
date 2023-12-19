package engineering.manageListView.listCells;


import beans.RequestBean1;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;

public class RequestListCellFactory implements Callback<ListView<RequestBean1>, ListCell<RequestBean1>> {

    @Override
    public ListCell<RequestBean1> call(ListView<RequestBean1> param) {
        return new ListCell<RequestBean1>() {
            private Parent parentNode = null;

            @Override
            protected void updateItem(RequestBean1 requestBean, boolean empty) {
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
                    itemNameLabel.setText(requestBean.getAthleteBean().getUsername());

                    setGraphic(parentNode);
                }
            }
        };
    }
}
