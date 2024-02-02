package viewone.popup.membershippopups;

import beans.MembershipBean;
import engineering.popups.PopupBaseClass;

import java.io.IOException;

public class MembershipPopUP extends PopupBaseClass {
    private MembershipConfirmPopupInterface castedInterface;
    protected MembershipPopUP(MembershipConfirmPopupInterface instanceOfParent, String file, String folder, int view) throws IOException {
        super(instanceOfParent, file, folder, view);
        castedInterface=(MembershipConfirmPopupInterface) this.caller;
    }
    public static PopupBaseClass getLoginPopup(MembershipConfirmPopupInterface caller, MembershipBean bean, String file, String folder, int view) throws IOException {
        if(PopupBaseClass.popupReference==null){
            PopupBaseClass.popupReference=new MembershipPopUP(caller,file,folder,view);
            ((MembershipPopupController)popupReference.getPopupController()).setBean(bean);
        }
        return PopupBaseClass.popupReference;
    }

    public void confirm(MembershipBean bean){
                castedInterface.conferma(bean);
    }
}
