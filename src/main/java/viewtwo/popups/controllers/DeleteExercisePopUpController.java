package viewtwo.popups.controllers;

import beans.ExerciseForWorkoutRoutineBean;
import engineering.popups.PopupBaseClass;
import engineering.popups.PopupBaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import viewtwo.popups.DeleteExePopUp;

public class DeleteExercisePopUpController extends PopupBaseController {
    DeleteExePopUp caller;
    @Override
    public void setCaller(PopupBaseClass caller) {
        this.caller=(DeleteExePopUp) caller;
    }
    private ExerciseForWorkoutRoutineBean exercise;

    @FXML Label repetitionsLabelNumber;
    @FXML Label setsLabelNumber;
    @FXML Label restLabelNumber;


    public void  setExeName(ExerciseForWorkoutRoutineBean exercise){
        this.exercise=exercise;
        restLabelNumber.setText(exercise.getRest());
        repetitionsLabelNumber.setText(String.valueOf(exercise.getRepetitions()));
        setsLabelNumber.setText(String.valueOf(exercise.getSets()));
    }

    @FXML
    public void deleteExercise(){
        caller.deleteExe(this.exercise);
    }
}
