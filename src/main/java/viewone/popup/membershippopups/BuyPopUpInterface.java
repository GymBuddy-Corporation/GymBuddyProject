package viewone.popup.membershippopups;

import beans.CardInfoBean;
import engineering.popups.PopupBaseInterface;

public interface  BuyPopUpInterface extends PopupBaseInterface {
    void buyMembership(CardInfoBean bean, boolean remember);
    void buyMembership();

}
