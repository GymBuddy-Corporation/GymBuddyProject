package viewone.popup.MembershipCoupons;

import beans.MembershipBean;
import engineering.popups.PopupBaseInterface;

public interface MembershipConfirmPopupInterface extends PopupBaseInterface {
    public void conferma(MembershipBean bean);
}
