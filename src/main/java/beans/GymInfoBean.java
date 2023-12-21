package beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class GymInfoBean {
    private final String name;
    private final String address;
    private final String city;
    private final String iban;
    private final String country;


    public GymInfoBean(String name, String address, String city, String iban, String country) {
        /*This is a constructor without syntax check and is used by controller*/
        this.name = name;
        this.address = address;
        this.city = city;
        this.iban = iban;
        this.country = country;
    }
    private void checkName(String name) /*throws InvalidUserInfoException, EmptyFieldsException*/ {
        if(name.isEmpty()){
            System.out.println("Name field empty.");
            /*throw new EmptyFieldsException();*/
        } else if(isNotValidLength(name)) {
            System.out.println("Name field invalid length.");
            /*throw new InvalidUserInfoException();*/
        }
    }

    private boolean isNotValidLength(String str) {
        return str.length() > 45;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.city;
    }

    public String getIban() {
        return this.iban;
    }
    public String getCountry() {
        return country;
    }


}
