package viewtwo.popups.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import viewtwo.popups.LoginPopUp;

import java.io.IOException;

public class LoginPopUpController {
    LoginPopUp caller;
    @FXML
    TextField inputEmail;
    @FXML
    PasswordField inputPassword;

    public void setCaller(LoginPopUp caller) {
        this.caller = caller;
    }
    @FXML
    public void actionLogin() throws IOException {
        caller.doLogin(inputEmail.getText(),inputPassword.getText());
    }
}
