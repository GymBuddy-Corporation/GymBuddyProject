package viewone.managelistview.listcells;


import beans.RequestBean;
import exceptions.logger.CostumeLogger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;

public class RequestListCellFactory implements Callback<ListView<RequestBean>, ListCell<RequestBean>> {

    @Override
    public ListCell<RequestBean> call(ListView<RequestBean> param) {
        return new ListCell<RequestBean>() {
            private Parent parentNode = null;

            @Override
            protected void updateItem(RequestBean requestBean, boolean empty) {
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
                            CostumeLogger.getInstance().logError(e);
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
