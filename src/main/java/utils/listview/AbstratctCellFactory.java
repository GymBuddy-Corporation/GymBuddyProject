package utils.listview;

import exceptions.logger.CostumeLogger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;

public abstract class AbstratctCellFactory implements Callback<ListView<Object>, ListCell<Object>> {

    @Override
    public ListCell<Object> call(ListView<Object> param) {
        return new ListCell<>() {
            private Parent parentNode = null;

            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (parentNode == null) {
                        try {
                            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(getListFXMLPathPath()));
                            parentNode = loader.load();
                        } catch (IOException e) {
                            CostumeLogger.getInstance().logError(e);
                        }
                    }
                    makeChangesToList(parentNode, item);
                    setGraphic(parentNode);
                }
            }
        };

    }
    public abstract String getListFXMLPathPath();
    public abstract void makeChangesToList(Parent parent,Object item);

}

