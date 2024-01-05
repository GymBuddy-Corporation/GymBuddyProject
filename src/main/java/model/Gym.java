package model;

import database.dao.GymDAO;
import engineering.ExerciseInventory;
import model.record.Credentials;
import model.record.Location;

import java.io.Serializable;
import java.util.List;

public class Gym extends User implements Serializable {
    private final String iban;
    private final String gymName;
    private ExerciseInventory gymExercises;
    private Location location;
    private List<String> gymCommunication;
    public Gym(String username, Credentials credentials, String iban, String gymName) {
        super(username, credentials);
        this.iban = iban;
        this.gymName= gymName;
    }

    public Gym(String username, Credentials credentials, String iban, String city, String address, String country, String gymName) {
        super(username, credentials);
        this.iban = iban;
        this.location = new Location(city, address, country);
        this.gymName = gymName;
        this.gymExercises = null;
    }

    public Gym(String username,String iban, String city, String address, String gymName){
        super(username);
        this.iban = iban;
        this.location = new Location(city, address, "noCountry");
        this.gymName = gymName;
    }
    public String getIban() {
        return iban;
    }

    public String getGymName() {
        return gymName;
    }

    public String getCity() {
        return location.city();
    }

    public String getAddress() {
        return location.address();
    }

    public String getCountry() {
        return location.country();
    }

    public ExerciseInventory getGymExercises() {
        lazyLoadExercises();
        return this.gymExercises;
    }
    //caricare gli esercizi solo nel momento effettivo del utilizzo
    private void lazyLoadExercises() {
        if (this.gymExercises == null) {
            this.gymExercises = ExerciseInventory.loadExcercise(this.gymName);
        }
    }

    public void addGymExercise(Exercise exerciseToAdd) {
        lazyLoadExercises();
        this.gymExercises.addExercise(exerciseToAdd);
    }
    public static List<String> loadComm(String gymName) {
        GymDAO gymdao=new GymDAO();
        return null;
    }

    public List<String> getGymCommunication() {
        if (this.gymExercises == null) {
            return (List<String>) (this.gymCommunication = loadComm(this.gymName));
        }
        return gymCommunication;
    }
}