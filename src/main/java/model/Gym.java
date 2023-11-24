package model;

/*import models.record.Credentials;
import models.record.PersonalInfo;*/

import java.io.Serializable;

public class Gym /*extends User*/ implements Serializable {
    private String username;

    public Gym(String username){
        this.username=username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /*public Gym(String username, PersonalInfo personalInfo, Credentials credentials, String iban){
        super(username, personalInfo, credentials);
        this.iban = iban;
    }*/
}