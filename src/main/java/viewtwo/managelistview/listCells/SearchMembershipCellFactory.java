package viewtwo.managelistview.listCells;

import beans.MembershipBean;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import utils.SwitchPage;
import utils.listview.AbstratctCellFactory;

public class SearchMembershipCellFactory extends AbstratctCellFactory {
    @Override
    public String getListFXMLPathPath() {
        return SwitchPage.getpath("SearchMembershipListView.fxml","athlete",2);
    }

    @Override
    public void makeChangesToList(Parent parent, Object item) {
        MembershipBean bean = (MembershipBean) item;
        Label nameLabel = (Label) parent.lookup("#name");
        nameLabel.setText(bean.getName());
        Label priceLabel = (Label) parent.lookup("#price");
        priceLabel.setText(String.valueOf(bean.getPrice())+"$");
        Label durationLabel = (Label) parent.lookup("#duration");
        durationLabel.setText(String.valueOf(bean.getDurationInDays())+" days");

    }
}
