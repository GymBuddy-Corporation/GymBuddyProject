package model;

import model.record.Credentials;
import model.record.PersonalInfo;


public class Trainer extends Person {
    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    private Gym gym;
    // private lista atleti
    public Trainer(String username, PersonalInfo personalInfo, Credentials credentials){
        super(username, credentials, personalInfo);
    }
}