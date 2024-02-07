package viewtwo.popups.controllers;

import beans.AthleteBean;
import beans.CredentialsBean;
import beans.PersonalInfoBean;
import engineering.popups.PopupBaseClass;
import engineering.popups.PopupBaseController;
import exceptions.dataexception.DataFieldException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    ComboBox genderField;

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

    public void userRegistration() throws DataFieldException {
        caller.userRegistration(
                new AthleteBean(usernameField.getText()
                ,new PersonalInfoBean( nameField.getText(),surnameField.getText(),dateField.getValue(), fiscalcodeField.getText(),getGenderFromChoice())
                ,CredentialsBean.ctorWithSyntaxCheck(emailField.getText(),passwordField.getText()), "GESTISCI L'ASSEGNAZIONE DEL PT")
        );
        caller.hidePopUp();
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
}
