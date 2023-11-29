package model;

import model.record.Credentials;

import java.io.Serializable;

public class Gym extends User implements Serializable {
    private final String iban;

    private final String city;
    private final String address;
    //TODO alex, sempre, ho messo qui il campo iban perchè secondo me potrebbe esserti utile
    // poi vedi te come vuoi implememtare il tuo use case e se ti puo essere utile

    public Gym(String username, Credentials credentials, String iban, String city, String address){
        super (username, credentials);
        this.iban = iban;
        this.city = city;
        this.address = address;
    }

    public String getIban() {
        return iban;
    }
}