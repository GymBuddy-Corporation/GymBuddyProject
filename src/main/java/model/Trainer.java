package model;

import model.record.Credentials;
import model.record.PersonalInfo;

import java.io.Serializable;

public class Trainer extends Person implements Serializable {

    public Trainer(String username, PersonalInfo personalInfo, Credentials credentials, Gym gym){
        super(username, credentials, personalInfo, gym);
    }
}