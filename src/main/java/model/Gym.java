package model;

import engineering.ExerciseInventory;
import model.record.Credentials;

import java.io.Serializable;

public class Gym extends User implements Serializable {
    private final String iban;
    private final String gymName;
    private final String city;

    private final ExerciseInventory gymExercises;
    private final String address;
    //TODO alex, sempre, ho messo qui il campo iban perchè secondo me potrebbe esserti utile
    // poi vedi te come vuoi implememtare il tuo use case e se ti puo essere utile

    public Gym(String username, Credentials credentials, String iban, String city, String address, String gymName, ExerciseInventory gymExercises){
        super (username, credentials);
        this.iban = iban;
        this.city = city;
        this.address = address;
        this.gymName=gymName;
        this.gymExercises = gymExercises;
    }

    public String getIban() {
        return iban;
    }

    public String getGymName() {
        return gymName;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public ExerciseInventory getGymExercises(){
        return this.gymExercises;
    }

    public void addGymExercise(Exercise exerciseToAdd){
        this.gymExercises.getExerciseList().add(exerciseToAdd);
    }
}