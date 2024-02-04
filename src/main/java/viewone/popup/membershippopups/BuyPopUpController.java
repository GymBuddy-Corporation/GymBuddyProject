package viewone.popup.membershippopups;

import beans.CardInfoBean;
import engineering.popups.PopupBaseClass;
import engineering.popups.PopupBaseController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;


public class BuyPopUpController extends PopupBaseController {
    BuyPopUp caller;
    @FXML
    TextField cardNumber;
    @FXML
    TextField name;
    @FXML
    TextField month;
    @FXML
    TextField year;
    @FXML
    TextField surname;
    @FXML
    CheckBox remember;


    @Override
    public void setCaller(PopupBaseClass caller) {
            this.caller= (BuyPopUp) caller;
    }

    public void buy1(){
        caller.buy(new CardInfoBean(cardNumber.getText(),month.getText(), year.getText(), name.getText(), surname.getText()),remember.isSelected());
    }

    public void buy2(){
        caller.buy();
    }

    public void decline (){
            caller.cancel();
    }

}
