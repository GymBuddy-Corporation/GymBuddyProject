package model;

import model.record.Credentials;
import model.record.PersonalInfo;

import java.io.Serializable;

public class Trainer extends Person implements Serializable {

    // private lista atleti
    public Trainer(String username, PersonalInfo personalInfo, Credentials credentials, Gym gym){
        super(username, credentials, personalInfo, gym);
    }
}