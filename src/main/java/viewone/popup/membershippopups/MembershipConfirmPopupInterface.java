package viewone.popup.membershippopups;

import beans.MembershipBean;
import engineering.popups.PopupBaseInterface;

public interface MembershipConfirmPopupInterface extends PopupBaseInterface {
    void conferma(MembershipBean bean);
}
