package viewtwo.popups;

import beans.ExerciseForWorkoutRoutineBean;
import engineering.popups.PopupBaseClass;
import engineering.popups.PopupBaseInterface;
import viewtwo.popups.abstracts.AddExeInterface;
import viewtwo.popups.abstracts.LoginPopUpInterface;
import viewtwo.popups.controllers.AddExercisePopUpController;

import java.io.IOException;

public class AddExePopUp extends PopupBaseClass {
    AddExeInterface castedInterface;
    protected AddExePopUp(PopupBaseInterface instanceOfParent, String file, String folder, int view) throws IOException {
        super(instanceOfParent, file, folder, view);
        castedInterface=(AddExeInterface) this.caller;
    }
    public static PopupBaseClass getAddExePopup(AddExeInterface caller,String nameExercise, String day,String file, String folder, int view) throws IOException {
        if(PopupBaseClass.popupReference==null){
            PopupBaseClass.popupReference=new AddExePopUp(caller,file,folder,view);
            ((AddExercisePopUpController)popupReference.getPopupController()).setExeName(nameExercise, day);
        }
        return PopupBaseClass.popupReference;
    }

    public void addExcercise(ExerciseForWorkoutRoutineBean bean){
        castedInterface.addExercise(bean);
        hidePopUp();
    }

}
