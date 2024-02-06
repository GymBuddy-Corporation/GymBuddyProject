package viewtwo.popups.controllers;

import engineering.popups.PopupBaseClass;
import engineering.popups.PopupBaseController;
import viewtwo.popups.MenuPopUp;

public abstract class MenuPopUpController extends PopupBaseController {
    //This class is only to be genarilized to make a menuPopUp
    private MenuPopUp caller;
    @Override
    public void setCaller(PopupBaseClass caller) {
            this.caller=(MenuPopUp) caller;
    }

    protected void clear(){
        caller.hidePopUp();
    }
}
