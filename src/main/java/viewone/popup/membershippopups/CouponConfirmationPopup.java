package viewone.popup.membershippopups;

import beans.CouponsBean;
import engineering.popups.PopupBaseClass;

import java.io.IOException;

public class CouponConfirmationPopup extends PopupBaseClass {
    private final CouponConfirmationInterface castedInterface;
    protected CouponConfirmationPopup(CouponConfirmationInterface instanceOfParent, String file, String folder, int view) throws IOException {
        super(instanceOfParent, file, folder, view);
        castedInterface=(CouponConfirmationInterface) this.caller;
    }
    public static PopupBaseClass getPopup(CouponConfirmationInterface caller, CouponsBean bean, String file, String folder, int view) throws IOException {
        if(PopupBaseClass.popupReference==null){
            PopupBaseClass.popupReference=new CouponConfirmationPopup(caller,file,folder,view);
            ((CouponConfirmationPopupController)popupReference.getPopupController()).setBean(bean);
        }
        return PopupBaseClass.popupReference;
    }

    public void confirm(CouponsBean bean){
        castedInterface.confermaCuoponPopup(bean);
        popupReference.hidePopUp();

    }
    public void decline(){
        popupReference.hidePopUp();
    }
}
