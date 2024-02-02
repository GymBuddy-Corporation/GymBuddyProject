package viewtwo.popups.abstracts;

import beans.WorkoutRoutineBean;
import engineering.popups.PopupBaseInterface;

public interface PersonalizeWorkoutRequestInterface extends PopupBaseInterface {
    void submitWorkoutRoutine(String comment, String name);
}
