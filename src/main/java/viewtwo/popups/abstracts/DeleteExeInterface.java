package viewtwo.popups.abstracts;

import beans.ExerciseForWorkoutRoutineBean;
import engineering.popups.PopupBaseInterface;

public interface DeleteExeInterface extends PopupBaseInterface {
    void deleteExercise(ExerciseForWorkoutRoutineBean bean);
}
