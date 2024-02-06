package viewtwo.popups.abstracts;

import beans.GymInfoBean;
import engineering.popups.PopupBaseInterface;

public interface SearchGymInterface extends PopupBaseInterface {
    void searchResult(GymInfoBean bean);
}
