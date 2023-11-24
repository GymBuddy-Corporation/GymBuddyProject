package beans;

/*import java.time.LocalTime;*/

import static beans.ExerciseStatusBean.Active;

public class ExerciseBean {
    private final String name;
    private ExerciseStatusBean statusExercise;

    public ExerciseBean(String name) {
        this.name = name;
        this.statusExercise = Active;
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

/*    private final int repetitions;
    private final int sets;
    private final String rest;


    public ExerciseBean(String name, int repetitions, int sets, String rest) {
        this.name = name;
        this.rest=rest;
        this.repetitions=repetitions;
        this.sets=sets;
    }

    public ExerciseBean(String name) {
        this.name = name;
    }*/