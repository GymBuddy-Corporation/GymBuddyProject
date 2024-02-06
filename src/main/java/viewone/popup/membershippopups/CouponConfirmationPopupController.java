package viewone.popup.membershippopups;

import beans.CouponsBean;
import engineering.popups.PopupBaseClass;
import engineering.popups.PopupBaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class CouponConfirmationPopupController extends PopupBaseController {
    CouponConfirmationPopup caller;

    @FXML
    Label nameLabel;
    @FXML
    Label priceLabel;
    @FXML
    Label typeLabel;
    @FXML
    Label onlyNewLabel;
    @FXML
    Label isComulableLabel;
    @FXML
    javafx.scene.control.TextArea descriptionArea;


    @Override
    public void setCaller(PopupBaseClass caller) {
            this.caller=(CouponConfirmationPopup) caller;
    }

    CouponsBean bean;
    public void setBean(CouponsBean bean){
        this.bean=bean;
        nameLabel.setText(bean.getName());
        priceLabel.setText(bean.getPointsPrice()+"points");
        typeLabel.setText(bean.getCouponType()+" "+bean.getCouponValue());
        descriptionArea.setText(bean.getDescription());
        onlyNewLabel.setText(bean.isOnlyForNewMembers()?"Only For New Members":"");
        isComulableLabel.setText(!bean.isCumulative()?"Not Comulable":"");
    }

    @FXML
    public void confirm(){
        caller.confirm(bean);
    }
    @FXML
    public void decline(){
        caller.decline();
    }

}
