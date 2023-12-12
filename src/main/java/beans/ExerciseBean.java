package beans;

public class ExerciseBean {
    private final String name;
    private ExerciseStatusBean statusExercise;

    private final String gym;

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
