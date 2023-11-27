package beans;

public class ExerciseBean {
    private final String name;
    private ExerciseStatusBean statusExercise;

    public ExerciseBean(String name, ExerciseStatusBean statusExercise) {
        this.name = name;
        this.statusExercise = statusExercise;
    }

    public String getName() {
        return name;
    }

    public ExerciseStatusBean getStatusExercise() {
        return statusExercise;
    }

    public void setStatusExercise(ExerciseStatusBean statusExercise) {
        this.statusExercise = statusExercise;
    }

    // New method to get ExerciseStatusBean from ExerciseBean
    public ExerciseStatusBean getExerciseStatusBean() {
        return statusExercise;
    }
}
