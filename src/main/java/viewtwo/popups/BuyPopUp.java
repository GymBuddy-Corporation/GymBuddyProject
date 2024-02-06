package viewtwo.popups;

import beans.CardInfoBean;
import engineering.popups.PopupBaseClass;
import viewtwo.popups.abstracts.BuyPopupInterface;
import viewtwo.popups.controllers.BuyPopController;

import java.io.IOException;

public class BuyPopUp extends PopupBaseClass {
    private final BuyPopupInterface castedInterface;
    protected BuyPopUp(BuyPopupInterface instanceOfParent, String file, String folder, int view) throws IOException {
        super(instanceOfParent, file, folder, view);
        castedInterface=(BuyPopupInterface) this.caller;
    }
    public static PopupBaseClass getSearchPopUp(BuyPopupInterface caller,String savedcard ,String file, String folder, int view) throws IOException {
        if(PopupBaseClass.popupReference==null){
            PopupBaseClass.popupReference=new BuyPopUp(caller,file,folder,view);
            ((BuyPopController)popupReference.getPopupController()).setCard(savedcard);
        }
        return PopupBaseClass.popupReference;
    }

    public void pay(CardInfoBean bean, boolean remember){
            castedInterface.buyMembership(bean,remember);
            hidePopUp();
    }

    public void pay(){
         castedInterface.buyMembership();
         hidePopUp();
    }
}
