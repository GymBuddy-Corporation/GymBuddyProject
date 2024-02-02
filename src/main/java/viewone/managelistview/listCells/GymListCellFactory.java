package viewone.managelistview.listCells;

import beans.GymInfoBean;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import utils.SwitchPage;
import utils.listview.AbstratctCellFactory;

public class GymListCellFactory extends AbstratctCellFactory {
    @Override
    public String getListFXMLPathPath() {
        return SwitchPage.getpath("ListGyms.fxml", "athlete", 1);
    }

    @Override
    public void makeChangesToList(Parent parent, Object item) {
        GymInfoBean gymBean = (GymInfoBean) item;
        Label gymNameLabel = (Label) parent.lookup("#gymLabel");
        gymNameLabel.setText(gymBean.getName());
        Label gymCityLabel = (Label) parent.lookup("#cityLabel");
        gymCityLabel.setText(gymBean.getCity());

    }
}
