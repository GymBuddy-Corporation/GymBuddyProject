package viewone.popup.membershippopups;

import beans.CouponsBean;
import engineering.popups.PopupBaseInterface;

public interface CouponConfirmationInterface extends PopupBaseInterface {
    void confermaCuoponPopup(CouponsBean bean);
}
