package beans;

import java.time.LocalTime;

public class ExerciseBean {
    private final String name;

    public ExerciseBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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