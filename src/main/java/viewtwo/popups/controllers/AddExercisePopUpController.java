package viewtwo.popups.controllers;

import beans.ExerciseForWorkoutRoutineBean;
import engineering.popups.PopupBaseClass;
import engineering.popups.PopupBaseController;
import javafx.fxml.FXML;
import viewtwo.popups.AddExePopUp;
public class AddExercisePopUpController extends PopupBaseController {

    AddExePopUp controllerApplicativo;
    @Override
    public void setCaller(PopupBaseClass caller) {
        controllerApplicativo=(AddExePopUp) caller;
    }
    private String exeName;

    public void  setExeName(String exeName){
                this.exeName=exeName;
    }

    @FXML
    public void addExcercise(){
        //costruisci il bean
        ExerciseForWorkoutRoutineBean bean=null;
        controllerApplicativo.addExcercise(bean);
    }


}
