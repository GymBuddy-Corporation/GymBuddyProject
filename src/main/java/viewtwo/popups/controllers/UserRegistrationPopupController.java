package viewtwo.popups.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import viewtwo.popups.PopupBaseClass;

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

    @Override
    public void setCaller(PopupBaseClass caller) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
                genderField.getItems().add("Male");
                genderField.getItems().add("Female");
                genderField.getItems().add("Not specified");
    }
}
