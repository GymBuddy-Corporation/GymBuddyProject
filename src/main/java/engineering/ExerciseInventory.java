package engineering;

import model.Exercise;
import java.util.List;

public class ExerciseInventory {

    private final List<Exercise> exerciseList;

    public ExerciseInventory(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void addExercise(Exercise exercise){
        this.exerciseList.add(exercise);
    }


}
