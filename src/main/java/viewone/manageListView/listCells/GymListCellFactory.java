package viewone.manageListView.listCells;

import beans.GymInfoBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import utils.SwitchPage;

import java.io.IOException;

public class GymListCellFactory implements Callback<ListView<GymInfoBean>, ListCell<GymInfoBean>> {

    @Override
    public ListCell<GymInfoBean> call(ListView<GymInfoBean> param) {
        return new ListCell<GymInfoBean>() {
            private Parent parentNode = null;

            @Override
            protected void updateItem(GymInfoBean gymBean, boolean empty) {
                super.updateItem(gymBean, empty);

                if (empty || gymBean == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (parentNode == null) {
                        try {
                            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(SwitchPage.getpath("ListGyms.fxml","athlete",1)));
                            parentNode = loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    Label gymNameLabel = (Label) parentNode.lookup("#gymLabel");
                    gymNameLabel.setText(gymBean.getName());
                    Label gymCityLabel = (Label) parentNode.lookup("#cityLabel");
                    gymCityLabel.setText(gymBean.getCity());

                    setGraphic(parentNode);
                }
            }
        };
    }
}

