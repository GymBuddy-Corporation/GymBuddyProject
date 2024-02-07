package model;

import engineering.ExerciseInventory;
import model.cupons.Coupon;
import model.record.Credentials;
import model.record.Location;

import java.util.Collections;
import java.util.List;

public class Gym extends User  {
    private final String iban;
    private final String gymName;
    private ExerciseInventory gymExercises;
    private Location location;
    private List<String> gymCommunication;
    private List<Membership> memberships;
    private List<Coupon> coupons;

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

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public List<Membership> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<Membership> memberships) {
        this.memberships = memberships;
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

    public void setGymExercises(List<Exercise> gymExercises) {
        this.gymExercises = new ExerciseInventory(gymExercises);
    }

    public void addGymExercise(Exercise exerciseToAdd) {
        this.gymExercises.addExercise(exerciseToAdd);
    }

    public List<String> getGymCommunication() {
        if (this.gymExercises == null) {
            this.gymCommunication = loadComm();
        }
        return gymCommunication;
    }

    public static List<String> loadComm() {
        //to be implemented just a stub
        return  Collections.emptyList();
    }
}