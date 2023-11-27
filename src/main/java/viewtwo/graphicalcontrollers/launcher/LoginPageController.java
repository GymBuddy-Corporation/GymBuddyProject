package viewtwo.graphicalcontrollers.launcher;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import viewtwo.popups.LoginPopUp;
import viewtwo.popups.abstracts.LoginPopUpInterface;

import java.io.IOException;

public class LoginPageController implements LoginPopUpInterface {
    @Override
    public void invokePopUp() throws IOException {
        LoginPopUp.getLoginPopup(this);
    }
    @FXML
    Label loginLabel;
    @FXML
    public void createPopUp() throws IOException {
        this.invokePopUp();
    }
    @Override
    public void loginCredentialInserted(String name, String password, boolean remember) {

    }
}
