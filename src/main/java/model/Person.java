package model;

import model.record.Credentials;
import model.record.PersonalInfo;

import java.time.LocalDate;

public abstract class Person extends User{

    private final PersonalInfo personalInfo;

    protected Person(String username, Credentials credentials,PersonalInfo personalInfo){
        super (username, credentials);
        this.personalInfo = personalInfo;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public String getName() {
        return personalInfo.name();
    }
    public String getSurname() {
        return personalInfo.surname();
    }
    public LocalDate getDateOfBirth() {
        return personalInfo.dateOfBirth();
    }
    public String getFC() {
        return personalInfo.fc();
    }
    public char getGender() {
        return personalInfo.gender();
    }

}
