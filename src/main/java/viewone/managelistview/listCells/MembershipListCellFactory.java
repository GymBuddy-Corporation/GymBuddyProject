package viewone.managelistview.listCells;

import beans.MembershipBean;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import utils.SwitchPage;
import utils.listview.AbstratctCellFactory;

public class MembershipListCellFactory extends AbstratctCellFactory {
    @Override
    public String getListFXMLPathPath() {
        return SwitchPage.getpath("ListMembership.fxml", "athlete", 1);

    }

    @Override
    public void makeChangesToList(Parent parent, Object item) {
        MembershipBean membershipBean=(MembershipBean) item;
        Label gymNameLabel = (Label) parent.lookup("#nameLabel");
        gymNameLabel.setText(membershipBean.getName());
        Label gymCityLabel = (Label) parent.lookup("#daysLabel");
        gymCityLabel.setText(membershipBean.getDurationInDays() +" days");
    }
}
