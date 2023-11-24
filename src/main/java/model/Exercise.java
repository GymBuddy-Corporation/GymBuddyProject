package model;

import java.io.Serializable;

public class Exercise implements Serializable {
    private int id; //chiarire bene l'uso che ne devo fare
    private final String name;
    private final Gym gym;
    private ExerciseStatus status;

    public Exercise(int id, String name, Gym gym){
        this.id = id;
        this.name = name;
        this.gym = gym;
        this.status=ExerciseStatus.ACTIVE;
    }

    public Exercise(String name, Gym gym){
        this.name = name;
        this.gym = gym;
        this.status=ExerciseStatus.ACTIVE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public Gym getGym() {
        return gym;
    }

    public ExerciseStatus getStatus() {
        return status;
    }

    public void setStatus(ExerciseStatus status) {
        this.status = status;
    }
}
