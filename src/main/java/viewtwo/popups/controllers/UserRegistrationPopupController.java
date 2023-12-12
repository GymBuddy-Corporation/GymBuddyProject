package viewtwo.popups.controllers;

import beans.AthleteBean;
import beans.CredentialsBean;
import beans.PersonalInfoBean;
import engineering.popups.PopupBaseController;
import exceptions.dataException.EmailFIeldException;
import exceptions.dataException.PasswordFieldException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import engineering.popups.PopupBaseClass;
import viewtwo.popups.UserRegistrationPopup;

import java.net.URL;
import java.util.ResourceBundle;

public class UserRegistrationPopupController extends PopupBaseController implements Initializable {
    @FXML
    TextField nameField;
    @FXML
    TextField surnameField;
    @FXML
    TextField usernameField;
    @FXML
    TextField fiscalcodeField;
    @FXML
    TextField cityField;
    @FXML
    TextField emailField;
    @FXML
    PasswordField passwordField;
    @FXML
    DatePicker dateField;
    @FXML
    ChoiceBox genderField;

    UserRegistrationPopup caller;
    @Override
    public void setCaller(PopupBaseClass caller) {
        this.caller=(UserRegistrationPopup) caller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
                genderField.getItems().add("Male");
                genderField.getItems().add("Female");
                genderField.getItems().add("Not specified");
    }
    private char getGenderFromChoice(){
        switch (genderField.getValue().toString()){
            case "Male":
                return 'm';
            case "Female":
                return 'f';
            default:
                return 'o';

        }
    }
    public void userRegistration() throws EmailFIeldException, PasswordFieldException {

        caller.userRegistration(
                new AthleteBean(usernameField.getText()
                ,new PersonalInfoBean( nameField.getText(),surnameField.getText(),dateField.getValue(), fiscalcodeField.getText(),getGenderFromChoice())
                ,CredentialsBean.ctorWithSyntaxCheck(emailField.getText(),passwordField.getText()))
        );
        caller.hidePopUp();
    }
}
