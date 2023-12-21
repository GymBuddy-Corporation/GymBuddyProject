package model;

import model.record.Credentials;
import model.record.PersonalInfo;

import java.time.LocalDate;

public abstract class User {
    protected String username;
    protected Credentials credentials;

    protected User() {}
    protected User(String username){
        this.username=username;
    }
    protected User(String username, Credentials credentials){
        this.username = username;
        this.credentials = credentials;
    }

    public String getEmail() {
        return credentials.email();
    }

    public String getPassword() {
        return credentials.password();
    }

    public String getUsername() {
        return username;
    }
}
