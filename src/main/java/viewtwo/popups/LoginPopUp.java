package viewtwo.popups;

import viewtwo.popups.abstracts.LoginPopUpInterface;
import viewtwo.popups.abstracts.PopupBaseInterface;

import java.io.IOException;

public class LoginPopUp extends PopupBaseClass{

    LoginPopUpInterface castedInterface;
    protected LoginPopUp(PopupBaseInterface instanceOfParent, String file, String folder, int view) throws IOException {
        super(instanceOfParent, file, folder, view);
        castedInterface=(LoginPopUpInterface) this.caller;
    }
    public  static PopupBaseClass getLoginPopup(LoginPopUpInterface caller,String file,String folder,int view) throws IOException {
        if(PopupBaseClass.popupReference==null){
            PopupBaseClass.popupReference=new LoginPopUp(caller,file,folder,view);
        }
        return PopupBaseClass.popupReference;
    }
    public void doLogin(String email, String password) throws IOException {
        castedInterface.loginCredentialInserted(email,password,true);
        hidePopUp();
    }
}
