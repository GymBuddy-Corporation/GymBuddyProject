package model;

import database.dao.GymDAO;
import engineering.ExerciseInventory;
import model.cupons.Cupon;
import model.record.Credentials;
import model.record.Location;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Gym extends User  {
    private final String iban;
    private final String gymName;
    private ExerciseInventory gymExercises;
    private Location location;

    public List<Cupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Cupon> coupons) {
        this.coupons = coupons;
    }

    private List<String> gymCommunication;

    public List<Membership> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<Membership> memberships) {
        this.memberships = memberships;
    }

    private List<Membership> memberships;
    private List<Cupon> coupons;
    public Gym(String username, Credentials credentials, String iban, String gymName) {
        super(username, credentials);
        this.iban = iban;
        this.gymName= gymName;
        this.gymExercises = null;
        this.coupons=null;
        this.memberships=null;
    }

    public Gym(String username, Credentials credentials, String iban, String city, String address, String country, String gymName) {
        super(username, credentials);
        this.iban = iban;
        this.location = new Location(city, address, country);
        this.gymName = gymName;
        this.gymExercises = null;
        this.coupons=null;
        this.memberships=null;
    }

    public Gym(String username,String iban, String city, String address, String gymName){
        super(username);
        this.iban = iban;
        this.location = new Location(city, address, "noCountry");
        this.gymName = gymName;
        this.gymExercises = null;
        this.coupons=null;
        this.memberships=null;
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
        return this.gymExercises;
    }

    public void addGymExercise(Exercise exerciseToAdd) {
        this.gymExercises.addExercise(exerciseToAdd);
    }

    public void setGymExercises(List<Exercise> gymExercises) {
        this.gymExercises = new ExerciseInventory(gymExercises);
    }

    public static List<String> loadComm(String gymName) {
        GymDAO gymdao=new GymDAO();

        return Collections.emptyList();
    }

    public List<String> getGymCommunication() {
        if (this.gymExercises == null) {
            this.gymCommunication = loadComm(this.gymName);
        }
        return gymCommunication;
    }
}