package viewone.popup.membershippopups;

import beans.CardInfoBean;
import engineering.popups.PopupBaseInterface;

public interface  BuyPopUpInterface extends PopupBaseInterface {
    public void buyMembership(CardInfoBean bean,boolean remember);
    public void buyMembership();

}
