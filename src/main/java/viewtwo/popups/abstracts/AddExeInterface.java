package viewtwo.popups.abstracts;

import beans.ExerciseForWorkoutRoutineBean;
import engineering.popups.PopupBaseInterface;

public interface AddExeInterface extends PopupBaseInterface {
    void addExercise(ExerciseForWorkoutRoutineBean bean);
}
