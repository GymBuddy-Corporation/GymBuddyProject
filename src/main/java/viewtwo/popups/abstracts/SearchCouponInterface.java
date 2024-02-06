package viewtwo.popups.abstracts;

import beans.CouponsBean;
import engineering.popups.PopupBaseInterface;

public interface SearchCouponInterface extends PopupBaseInterface {
    void selectedCoupon(CouponsBean bean);
}
