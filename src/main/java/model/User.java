package model;

import model.record.Credentials;

public abstract class User {
    private String username;
    private Credentials credentials;

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
