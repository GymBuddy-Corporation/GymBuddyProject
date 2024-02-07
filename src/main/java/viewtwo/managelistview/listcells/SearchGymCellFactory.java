package viewtwo.managelistview.listcells;

import beans.GymInfoBean;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import utils.SwitchPage;
import utils.listview.AbstratctCellFactory;

public class SearchGymCellFactory extends AbstratctCellFactory {
    @Override
    public String getListFXMLPathPath() {
        return SwitchPage.getpath("SearchGymListView.fxml","athlete",2);
    }

    @Override
    public void makeChangesToList(Parent parent, Object item) {
        GymInfoBean bean = (GymInfoBean) item;
        Label nameLabel = (Label) parent.lookup("#gymName");
        nameLabel.setText(bean.getName());
    }
}
