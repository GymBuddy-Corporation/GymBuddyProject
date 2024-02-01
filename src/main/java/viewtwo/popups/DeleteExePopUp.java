package viewtwo.popups;

import beans.ExerciseForWorkoutRoutineBean;
import engineering.popups.PopupBaseClass;
import engineering.popups.PopupBaseInterface;
import viewtwo.popups.abstracts.DeleteExeInterface;
import viewtwo.popups.controllers.AddExercisePopUpController;
import viewtwo.popups.controllers.DeleteExercisePopUpController;

import java.io.IOException;

public class DeleteExePopUp extends PopupBaseClass {
    DeleteExeInterface castedInterface;
    protected DeleteExePopUp(PopupBaseInterface instanceOfParent, String file, String folder, int view) throws IOException {
        super(instanceOfParent, file, folder, view);
        castedInterface=(DeleteExeInterface) this.caller;
    }
    public static PopupBaseClass getDeleteExePopup(DeleteExeInterface caller,ExerciseForWorkoutRoutineBean Exercise, String file, String folder, int view) throws IOException {
        if(PopupBaseClass.popupReference==null){
            PopupBaseClass.popupReference=new DeleteExePopUp(caller,file,folder,view);
            ((DeleteExercisePopUpController)popupReference.getPopupController()).setExeName(Exercise);
        }
        return PopupBaseClass.popupReference;
    }

    public void deleteExe(ExerciseForWorkoutRoutineBean bean){
        castedInterface.deleteExercise(bean);
        hidePopUp();
    }

}
