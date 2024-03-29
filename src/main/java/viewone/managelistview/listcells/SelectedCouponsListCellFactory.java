package viewone.managelistview.listcells;

import beans.CouponsBean;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import utils.SwitchPage;
import utils.listview.AbstratctCellFactory;

public class SelectedCouponsListCellFactory extends AbstratctCellFactory {
    @Override
    public String getListFXMLPathPath() {
        return SwitchPage.getpath("ListSelectedCoupons.fxml","athlete",1);
    }

    @Override
    public void makeChangesToList(Parent parent, Object item) {
        CouponsBean bean = (CouponsBean) item;
        Label nameLabel = (Label) parent.lookup("#nameLabel");
        nameLabel.setText(bean.getName());
    }
}
