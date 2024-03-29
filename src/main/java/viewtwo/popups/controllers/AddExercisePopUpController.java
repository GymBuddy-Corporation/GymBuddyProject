package viewtwo.popups.controllers;

import beans.ExerciseForWorkoutRoutineBean;
import engineering.popups.PopupBaseClass;
import engineering.popups.PopupBaseController;
import exceptions.dataexception.DataFieldException;
import exceptions.logger.CostumeLogger;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import viewtwo.popups.AddExePopUp;

public class AddExercisePopUpController extends PopupBaseController {
    @FXML TextField repTextField;
    @FXML TextField setsTextField;
    @FXML TextField restTextField;
    AddExePopUp caller;
    @Override
    public void setCaller(PopupBaseClass caller) {
        this.caller=(AddExePopUp) caller;
    }
    private String exeName;
    private String day;

    public void  setExeName(String exeName, String day){
        this.exeName=exeName;
        this.day=day;
    }

    @FXML
    public void addExercise(){
        ExerciseForWorkoutRoutineBean newExercise= new ExerciseForWorkoutRoutineBean(exeName, day);
        String repsString = repTextField.getText();
        String setsString = setsTextField.getText();
        String rest = restTextField.getText();
        try {
            int repetitions = Integer.parseInt(repsString);
            int sets = Integer.parseInt(setsString);
            newExercise.setRepetitions(repetitions);
            newExercise.setSets(sets);
            newExercise.setRest(rest);
            caller.addExcercise(newExercise);
        } catch (NumberFormatException e) {
            CostumeLogger.getInstance().logError(e);
            caller.hidePopUp();
        }catch (DataFieldException e){
                e.callMe(2);
                caller.hidePopUp();

        }
    }
}
