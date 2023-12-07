package viewtwo.popups;

import beans.AthleteBean;
import engineering.popups.PopupBaseClass;
import viewtwo.popups.abstracts.UserRegistrationPopupInterface;

import java.io.IOException;

public class UserRegistrationPopup extends PopupBaseClass {
    protected UserRegistrationPopup(UserRegistrationPopupInterface instanceOfParent, String file, String folder, int view) throws IOException {
        super(instanceOfParent, file, folder, view);
    }
    public  static PopupBaseClass getRegistrationPopup(UserRegistrationPopupInterface caller, String file, String folder, int view) throws IOException {
        if(PopupBaseClass.popupReference==null){
            PopupBaseClass.popupReference=new UserRegistrationPopup(caller,file,folder,view);
        }
        return PopupBaseClass.popupReference;
    }

    public void userRegistration(AthleteBean bean){
        ((UserRegistrationPopupInterface)caller).userRegistration(bean);
    }



}
