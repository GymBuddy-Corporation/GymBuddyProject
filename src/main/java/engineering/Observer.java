package engineering;

import model.Exercise;
import model.ExerciseStatus;

public interface Observer {
    void update(Exercise exercise);
}
