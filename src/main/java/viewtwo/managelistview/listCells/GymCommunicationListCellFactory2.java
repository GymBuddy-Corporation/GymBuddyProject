package viewtwo.managelistview.listCells;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;

public class GymCommunicationListCellFactory2 implements Callback<ListView<String>, ListCell<String>> {

    @Override
    public ListCell<String> call(ListView<String> param) {
        return new GymCommunicationListCell();
    }

    private static class GymCommunicationListCell extends ListCell<String> {
        private static final String FXML_PATH = "/viewone/pt/ListGymCommunication.fxml";
        private final Label itemNameLabel;

        public GymCommunicationListCell() {
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
        protected void updateItem(String info, boolean empty) {
            super.updateItem(info, empty);

            if (empty || info == null) {
                setText(null);
            } else {
                itemNameLabel.setText(info);
            }
        }
    }
}
