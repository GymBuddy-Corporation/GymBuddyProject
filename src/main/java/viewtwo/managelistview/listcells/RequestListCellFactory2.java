package viewtwo.managelistview.listcells;

import beans.RequestBean;
import exceptions.logger.CostumeLogger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;

public class RequestListCellFactory2 implements Callback<ListView<RequestBean>, ListCell<RequestBean>> {

    @Override
    public ListCell<RequestBean> call(ListView<RequestBean> param) {
        return new RequestListCell();
    }
    private static class RequestListCell extends ListCell<RequestBean> {
        private  Parent parentNode;
        private  Label itemNameLabel;

        public RequestListCell() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewtwo/pt/ListRequest.fxml"));
                parentNode = loader.load();
                itemNameLabel = (Label) parentNode.lookup("#itemName1");
            } catch (IOException e) {
                CostumeLogger.getInstance().logError(e);
            }
        }

        @Override
        protected void updateItem(RequestBean requestBean, boolean empty) {
            super.updateItem(requestBean, empty);

            if (empty || requestBean == null) {
                setText(null);
                setGraphic(null);
            } else {
                itemNameLabel.setText(requestBean.getAthleteBean().getUsername());
                setGraphic(parentNode);
            }
        }
    }
}
