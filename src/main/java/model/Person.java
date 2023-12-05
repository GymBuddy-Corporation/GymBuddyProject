package model;

import model.record.Credentials;
import model.record.PersonalInfo;

import java.time.LocalDate;

public abstract class Person extends User{

    private final PersonalInfo personalInfo;
    private final Gym gym;

    protected Person(String username, Credentials credentials,PersonalInfo personalInfo, Gym gym){
        super (username, credentials);
        this.personalInfo = personalInfo;
        this.gym = gym;
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

    public Gym getGym() {
        return gym;
    }
}
