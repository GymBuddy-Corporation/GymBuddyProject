package viewtwo.popups.controllers;

import beans.CardInfoBean;
import engineering.popups.PopupBaseClass;
import engineering.popups.PopupBaseController;
import exceptions.dataexception.DataFieldException;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import viewtwo.popups.BuyPopUp;

public class BuyPopController extends PopupBaseController {
    BuyPopUp caller;
    @FXML
    private TextField month;

    @FXML
    private TextField name;

    @FXML
    private TextField number;

    @FXML
    private CheckBox oldCard;

    @FXML
    private CheckBox remember;

    @FXML
    private TextField surname;

    @FXML
    private TextField year;

    @FXML
    void buy()  {
            if(oldCard.isSelected())
            {caller.pay();}else{
        try {
            caller.pay(new CardInfoBean(number.getText(),month.getText(),year.getText(),name.getText(),surname.getText()),remember.isSelected());
        } catch (DataFieldException e) {
            e.callMe(2);
        }}
    }

    @Override
    public void setCaller(PopupBaseClass caller) {
            this.caller= (BuyPopUp) caller;
    }

    public void setCard(String savedcard) {
        if(savedcard==null){
            oldCard.setVisible(false);
            return;
        }
        oldCard.setText("pay with:"+savedcard);
    }
}
