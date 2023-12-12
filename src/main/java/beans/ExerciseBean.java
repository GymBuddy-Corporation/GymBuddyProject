package beans;

public class ExerciseBean {
    protected final String name;
    protected ExerciseStatusBean statusExercise;

    protected final String gym;

    public ExerciseBean(String name, ExerciseStatusBean statusExercise, String gym) {
        this.name = name;
        this.statusExercise = statusExercise;
        this.gym = gym;
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

    public String getGym() {
        return gym;
    }
}
