package viewone.managelistview.listcells;

import beans.CuponsBean;
import beans.GymInfoBean;
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
        CuponsBean bean = (CuponsBean) item;
        Label nameLabel = (Label) parent.lookup("#nameLabel");
        nameLabel.setText(bean.getName());
    }
}
