package viewtwo.popups;

import beans.WorkoutRoutineBean;
import engineering.popups.PopupBaseClass;
import engineering.popups.PopupBaseInterface;
import viewtwo.popups.abstracts.PersonalizeWorkoutRequestInterface;

import java.io.IOException;

public class PersonalizeWRPopUp extends PopupBaseClass {
    PersonalizeWorkoutRequestInterface castedInterface;
    protected PersonalizeWRPopUp(PopupBaseInterface instanceOfParent, String file, String folder, int view) throws IOException {
        super(instanceOfParent, file, folder, view);
        castedInterface=(PersonalizeWorkoutRequestInterface) this.caller;
    }
    public static PopupBaseClass getPersonalizeWRPopup(PersonalizeWorkoutRequestInterface caller, WorkoutRoutineBean wr, String file, String folder, int view) throws IOException {
        if(PopupBaseClass.popupReference==null){
            PopupBaseClass.popupReference=new PersonalizeWRPopUp(caller,file,folder,view);
        }
        return PopupBaseClass.popupReference;
    }

    public void submitWR(String comment, String name){
        castedInterface.submitWorkoutRoutine(comment, name);
        hidePopUp();
    }

}
