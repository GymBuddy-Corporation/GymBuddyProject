package model;

import model.record.Credentials;

import java.io.Serializable;

public class Gym /*extends User CHIARISCI*/implements Serializable {
    private String username;
    private Credentials credentials;
    private String iban;

    public Gym(String username){
        this.username=username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Gym(String username, Credentials credentials, String iban){
        this.username = username;
        this.credentials = credentials;
        this.iban = iban;
    }
    public String getEmail() {
        return credentials.email();
    }

    public String getPassword() {
        return credentials.password();
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}