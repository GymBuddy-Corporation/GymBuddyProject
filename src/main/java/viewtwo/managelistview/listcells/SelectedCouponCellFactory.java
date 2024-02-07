package viewtwo.managelistview.listcells;

import beans.CouponsBean;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import utils.SwitchPage;
import utils.listview.AbstratctCellFactory;

public class SelectedCouponCellFactory extends AbstratctCellFactory {
    @Override
    public String getListFXMLPathPath() {
        return SwitchPage.getpath("SelectedCouponListView.fxml","athlete",2);
    }

    @Override
    public void makeChangesToList(Parent parent, Object item) {
        CouponsBean bean = (CouponsBean) item;
        Label nameLabel = (Label) parent.lookup("#name");
        nameLabel.setText(bean.getName());
    }
}
