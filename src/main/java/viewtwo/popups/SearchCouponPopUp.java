package viewtwo.popups;

import beans.CouponsBean;
import engineering.popups.PopupBaseClass;
import viewtwo.popups.abstracts.SearchCouponInterface;
import viewtwo.popups.controllers.SearchCouponPopUpController;

import java.io.IOException;
import java.util.List;

public class SearchCouponPopUp  extends PopupBaseClass {
    private final SearchCouponInterface castedInterface;
    protected SearchCouponPopUp(SearchCouponInterface instanceOfParent, String file, String folder, int view) throws IOException {
        super(instanceOfParent, file, folder, view);
        castedInterface=(SearchCouponInterface) this.caller;
    }
    public static PopupBaseClass getSearchPopUp(SearchCouponInterface caller, List<CouponsBean> beans, String file, String folder, int view) throws IOException {
        if(PopupBaseClass.popupReference==null){
            PopupBaseClass.popupReference=new SearchCouponPopUp(caller,file,folder,view);
            ((SearchCouponPopUpController)popupReference.getPopupController()).setBean(beans);
        }
        return PopupBaseClass.popupReference;
    }

    public void selectedCoupon(CouponsBean bean){
        castedInterface.selectedCoupon(bean);
        hidePopUp();
    }

}
