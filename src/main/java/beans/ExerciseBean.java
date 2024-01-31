package beans;

import model.ExerciseStatus;

public class ExerciseBean {
    protected final String name;
    protected ExerciseStatus statusExercise;

    public ExerciseBean(String name, ExerciseStatus statusExercise) {
        this.name = name;
        this.statusExercise = statusExercise;
    }

    public ExerciseBean(String name) {
        this.name = name;
        setStatusExercise(ExerciseStatus.ACTIVE);
    }

    public String getName() {
        return name;
    }

    public ExerciseStatus getStatusExercise() {
        return statusExercise;
    }

    public void setStatusExercise(ExerciseStatus statusExercise) {
        this.statusExercise = statusExercise;
    }
}
