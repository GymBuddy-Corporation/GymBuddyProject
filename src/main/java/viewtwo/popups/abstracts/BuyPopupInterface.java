package viewtwo.popups.abstracts;

import beans.CardInfoBean;
import engineering.popups.PopupBaseInterface;

public interface BuyPopupInterface extends PopupBaseInterface {
    void buyMembership();
    void buyMembership(CardInfoBean bean,boolean remember);
}
