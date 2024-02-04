package viewtwo.popups.abstracts;

import engineering.popups.PopupBaseInterface;

public interface PersonalizeWorkoutRequestInterface extends PopupBaseInterface {
    void submitWorkoutRoutine(String comment, String name);
}
