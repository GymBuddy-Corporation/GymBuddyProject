package viewtwo.managelistview.listcells;

import beans.CouponsBean;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import utils.SwitchPage;
import utils.listview.AbstratctCellFactory;

public class SearchCouponsCellFactory extends AbstratctCellFactory {
    @Override
    public String getListFXMLPathPath() {
        return SwitchPage.getpath("SearchCouponsListView.fxml","athlete",2);
    }

    @Override
    public void makeChangesToList(Parent parent, Object item) {
        CouponsBean bean = (CouponsBean) item;
        Label nameLabel = (Label) parent.lookup("#name");
        nameLabel.setText(bean.getName());
        Label priceLabel = (Label) parent.lookup("#price");
        priceLabel.setText(bean.getPointsPrice()+"points");
        Label valueLabel = (Label) parent.lookup("#value");
        valueLabel.setText(bean.getCouponValue());
        CheckBox checkNewMembersBox=(CheckBox) parent.lookup("#onlyNewMembers");
        checkNewMembersBox.setSelected(bean.isOnlyForNewMembers());
        CheckBox checkCumulableBox=(CheckBox) parent.lookup("#cumulable");
        checkCumulableBox.setSelected(bean.isCumulative());
    }
}
