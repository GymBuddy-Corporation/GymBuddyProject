package viewtwo.popups.controllers;

import beans.ExerciseForWorkoutRoutineBean;
import beans.RequestBean;
import beans.WorkoutRoutineBean;
import engineering.popups.PopupBaseClass;
import engineering.popups.PopupBaseController;
import exceptions.dataException.DataFieldException;
import exceptions.dataException.TyperEnumerations.FieldsEnum;
import exceptions.dataException.TyperEnumerations.ProblemEnum;
import exceptions.logger.CostumeLogger;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import viewtwo.popups.AddExePopUp;
import viewtwo.popups.PersonalizeWRPopUp;

import java.io.IOException;

public class PersonalizeWRPopUpController extends PopupBaseController {
    @FXML TextField nameTextField;
    @FXML TextArea commentTextArea;
    PersonalizeWRPopUp caller;
    @Override
    public void setCaller(PopupBaseClass caller) {
        this.caller=(PersonalizeWRPopUp) caller;
    }

    @FXML public void personalizeWR(){
        try {
            String name = nameTextField.getText();
            String comment = commentTextArea.getText();
            caller.submitWR(comment, name);
        } catch (NullPointerException e){
            CostumeLogger.getInstance().logError(e);
        }
    }
}
