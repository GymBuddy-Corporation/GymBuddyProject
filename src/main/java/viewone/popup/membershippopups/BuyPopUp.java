package viewone.popup.membershippopups;

import beans.CardInfoBean;
import engineering.popups.PopupBaseClass;

import java.io.IOException;

public class BuyPopUp extends PopupBaseClass {
    private final BuyPopUpInterface castedInterface;
    protected BuyPopUp(BuyPopUpInterface instanceOfParent, String file, String folder, int view) throws IOException {
        super(instanceOfParent, file, folder, view);
        castedInterface=(BuyPopUpInterface) this.caller;
    }
    public static PopupBaseClass getPayPopUp(BuyPopUpInterface caller,String file, String folder, int view) throws IOException {
        if(PopupBaseClass.popupReference==null){
            PopupBaseClass.popupReference=new BuyPopUp(caller,file,folder,view);
        }
        return PopupBaseClass.popupReference;
    }

    public void  buy(CardInfoBean bean,boolean val){
        castedInterface.buyMembership(bean,val);
        hidePopUp();
    }
    public void buy(){
        castedInterface.buyMembership();
        hidePopUp();
    }

    public void cancel(){
        hidePopUp();
    }


}
