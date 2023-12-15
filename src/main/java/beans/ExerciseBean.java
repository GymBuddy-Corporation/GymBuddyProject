package beans;

public class ExerciseBean {
    protected final String name;
    protected ExerciseStatusBean statusExercise;

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
}
