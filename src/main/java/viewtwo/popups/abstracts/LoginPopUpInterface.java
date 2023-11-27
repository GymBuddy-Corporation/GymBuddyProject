package viewtwo.popups.abstracts;

import javafx.stage.Popup;
import viewtwo.popups.LoginPopUp;

import java.io.IOException;

public interface LoginPopUpInterface {
    public void invokePopUp() throws IOException;
    public void loginCredentialInserted(String name,String password,boolean remember);
}
