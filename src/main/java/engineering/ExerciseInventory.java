package engineering;

import model.Exercise;
import model.ExerciseStatus;

import java.util.List;

public class ExerciseInventory {

    private final List<Exercise> exerciseList;

    public ExerciseInventory(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }
}
