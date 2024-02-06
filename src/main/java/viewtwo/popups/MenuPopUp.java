package viewtwo.popups;

import engineering.popups.PopupBaseClass;
import engineering.popups.PopupBaseInterface;
import exceptions.logger.CostumeLogger;
import viewtwo.popups.abstracts.MenuPopupInterface;

import java.io.IOException;

public class MenuPopUp extends PopupBaseClass {
    PopupBaseInterface casetedInterface;
    protected MenuPopUp(MenuPopupInterface instanceOfParent, String file, String folder, int view) throws IOException {
        super(instanceOfParent, file, folder, view);
        this.casetedInterface=instanceOfParent;
    }
    public static PopupBaseClass getMenu(MenuPopupInterface caller, String file, String folder, int view) {
        if(PopupBaseClass.popupReference==null){
            try {
                PopupBaseClass.popupReference=new MenuPopUp(caller,file,folder,view);
            } catch (IOException e) {
                CostumeLogger.getInstance().logError(e);
                return null;
            }
        }
        return PopupBaseClass.popupReference;
    }

    public void clear(){
        hidePopUp();
    }
}
