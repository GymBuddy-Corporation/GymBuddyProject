package model;

import model.record.Credentials;
import model.record.PersonalInfo;

import java.io.Serializable;

public class Trainer extends Person implements Serializable {
    private String iban;

    public Trainer(String username, PersonalInfo personalInfo, Credentials credentials){
        super(username, credentials, personalInfo);
    }
}