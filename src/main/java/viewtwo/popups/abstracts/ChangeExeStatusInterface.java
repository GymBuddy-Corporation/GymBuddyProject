package viewtwo.popups.abstracts;

import beans.ExerciseBean;
import engineering.popups.PopupBaseInterface;

public interface ChangeExeStatusInterface extends PopupBaseInterface {
    void setExerciseStatus(ExerciseBean bean);
}
