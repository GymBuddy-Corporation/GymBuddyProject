package viewtwo.popups.abstracts;

import javafx.stage.Popup;
import viewtwo.popups.LoginPopUp;

import java.io.IOException;

public interface LoginPopUpInterface extends PopupBaseInterface{
    public void loginCredentialInserted(String name,String password,boolean remember) throws IOException;
}
