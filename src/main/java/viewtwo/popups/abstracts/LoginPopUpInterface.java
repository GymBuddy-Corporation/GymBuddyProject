package viewtwo.popups.abstracts;

import engineering.popups.PopupBaseInterface;

import java.io.IOException;

public interface LoginPopUpInterface extends PopupBaseInterface {
    public void loginCredentialInserted(String name,String password,boolean remember) throws IOException;
}
