package model;

import engineering.Observable;

import java.io.Serializable;

public class Exercise extends Observable implements Serializable  {
    protected String name;
    protected ExerciseStatus status;

    public Exercise(String name, ExerciseStatus status){
        //ideato SOLO per passare ora i parametri (che non ho il DB)
        this.name = name;
        this.status = status;
    }

    public Exercise(String name){
        //ideato per essere usato alla creazione di un esercizio
        this.name = name;
        this.status=ExerciseStatus.ACTIVE;
    }

    public Exercise(){
    }

    public String getName() {
        return name;
    }

    public ExerciseStatus getStatus() {
        return status;
    }

    public void setStatus(ExerciseStatus status) {
        this.status = status;
        super.notifyObservers(this.getName(), status);
    }
}
