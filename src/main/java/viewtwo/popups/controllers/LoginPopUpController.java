package viewtwo.popups.controllers;

import engineering.popups.PopupBaseClass;
import engineering.popups.PopupBaseController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import viewtwo.popups.LoginPopUp;

import java.io.IOException;

public class LoginPopUpController extends PopupBaseController {
    LoginPopUp caller;
    @FXML
    TextField inputEmail;
    @FXML
    PasswordField inputPassword;
    @FXML
    ToggleButton yesToggle;
    @FXML
    ToggleGroup remember;

    @Override
    public void setCaller(PopupBaseClass caller) {
        this.caller =(LoginPopUp) caller;
        remember.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });
    }
    @FXML
    public void actionLogin() throws IOException {
        caller.doLogin(inputEmail.getText(),inputPassword.getText(),yesToggle.isSelected());

    }
}
