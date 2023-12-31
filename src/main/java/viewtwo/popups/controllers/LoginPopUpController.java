package viewtwo.popups.controllers;

import engineering.popups.PopupBaseController;
import exceptions.CostumException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import viewtwo.popups.LoginPopUp;
import engineering.popups.PopupBaseClass;

import java.io.IOException;

public class LoginPopUpController extends PopupBaseController {
    LoginPopUp caller;
    @FXML
    TextField inputEmail;
    @FXML
    PasswordField inputPassword;



    @Override
    public void setCaller(PopupBaseClass caller) {
        this.caller =(LoginPopUp) caller;
    }
    @FXML
    public void actionLogin() throws IOException {
        caller.doLogin(inputEmail.getText(),inputPassword.getText());
    }
}
