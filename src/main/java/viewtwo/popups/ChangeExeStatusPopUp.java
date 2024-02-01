package viewtwo.popups;

import beans.ExerciseBean;
import engineering.popups.PopupBaseClass;
import engineering.popups.PopupBaseInterface;
import viewtwo.popups.abstracts.ChangeExeStatusInterface;

import java.io.IOException;

public class ChangeExeStatusPopUp extends PopupBaseClass {
    ChangeExeStatusInterface castedInterface;
    protected ChangeExeStatusPopUp(PopupBaseInterface instanceOfParent, String file, String folder, int view) throws IOException {
        super(instanceOfParent, file, folder, view);
        castedInterface=(ChangeExeStatusInterface) this.caller;
    }
    public static PopupBaseClass getChangeExeStatusPopup(ChangeExeStatusInterface caller, String file, String folder, int view) throws IOException {
        if(PopupBaseClass.popupReference==null){
            PopupBaseClass.popupReference=new ChangeExeStatusPopUp(caller,file,folder,view);
        }
        return PopupBaseClass.popupReference;
    }

    public void setExeStatus(ExerciseBean bean){
        castedInterface.setExerciseStatus(bean);
        hidePopUp();
    }

}
