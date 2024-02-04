package viewone.popup.membershippopups;

import beans.CuponsBean;
import engineering.popups.PopupBaseClass;

import java.io.IOException;

public class CouponConfirmationPopup extends PopupBaseClass {
    private final CouponConfirmationInterface castedInterface;
    protected CouponConfirmationPopup(CouponConfirmationInterface instanceOfParent, String file, String folder, int view) throws IOException {
        super(instanceOfParent, file, folder, view);
        castedInterface=(CouponConfirmationInterface) this.caller;
    }
    public static PopupBaseClass getPopup(CouponConfirmationInterface caller, CuponsBean bean, String file, String folder, int view) throws IOException {
        if(PopupBaseClass.popupReference==null){
            PopupBaseClass.popupReference=new CouponConfirmationPopup(caller,file,folder,view);
            ((CouponConfirmationPopupController)popupReference.getPopupController()).setBean(bean);
        }
        return PopupBaseClass.popupReference;
    }

    public void confirm(CuponsBean bean){
        castedInterface.confermaCuoponPopup(bean);
        popupReference.hidePopUp();

    }
    public void decline(){
        popupReference.hidePopUp();
    }
}
