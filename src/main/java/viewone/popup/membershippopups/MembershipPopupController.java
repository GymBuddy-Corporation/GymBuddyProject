package viewone.popup.membershippopups;

import beans.MembershipBean;
import engineering.popups.PopupBaseClass;
import engineering.popups.PopupBaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class MembershipPopupController extends PopupBaseController {
    MembershipPopUP caller;
    private MembershipBean bean;

    @FXML
    Label name;
    @FXML
    Label priceLabel;

    @FXML
    Label durationLabel;

    @FXML
    Label pointsLabel;

    @FXML
    TextArea descriptionArea;


    @Override
    public void setCaller(PopupBaseClass caller) {
            this.caller=(MembershipPopUP) caller;
    }

    public void setBean(MembershipBean bean){
                this.bean=bean;
                name.setText(bean.getName());
                durationLabel.setText(bean.getDurationInDays() +" days");
                priceLabel.setText(bean.getPrice()+" $");
                pointsLabel.setText(bean.getPointsAwardedOnBuy()+" points");
                descriptionArea.setText(bean.getDescription());
    }


    @FXML
    public void confirm(){
        caller.confirm(bean);

    }
    @FXML
    public void decline(){caller.decline();}
}
