package engineering;

import model.ExerciseStatus;

public interface Observer {
    void update(String exerciseName, ExerciseStatus status);
}