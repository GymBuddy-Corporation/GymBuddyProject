package viewtwo.popups.controllers;

import beans.ExerciseForWorkoutRoutineBean;
import beans.RequestBean;
import beans.WorkoutRoutineBean;
import engineering.popups.PopupBaseClass;
import engineering.popups.PopupBaseController;
import exceptions.dataException.DataFieldException;
import exceptions.logger.CostumeLogger;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import viewtwo.popups.AddExePopUp;
import viewtwo.popups.PersonalizeWRPopUp;

import java.io.IOException;

public class PersonalizeWRPopUpController extends PopupBaseController {
    @FXML TextField nameTextField;
    @FXML TextField commentTextField;
    PersonalizeWRPopUp caller;
    @Override
    public void setCaller(PopupBaseClass caller) {
        this.caller=(PersonalizeWRPopUp) caller;
    }

    @FXML
    public void personalizeWR(){
        String name = nameTextField.getText();
        String comment = commentTextField.getText();
        caller.submitWR(name, comment);
        //todo
    }
}
