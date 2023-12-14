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

    public GymInfoBean(String name, String address, String city, String iban) {
        /*This is a constructor without syntax check and is used by controller*/
        this.name = name;
        this.address = address;
        this.city = city;
        this.iban = iban;
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


    private boolean isValidFc(String fc) {
        return Pattern.matches("^([A-Z]{6}[\\dLMNPQRSTUV]{2}[ABCDEHLMPRST][\\dLMNPQRSTUV]{2}[A-Z][\\dLMNPQRSTUV]{3}[A-Z])$|(\\d{11})$",fc);
    }

    private void checkSurname(String surname) /*throws InvalidUserInfoException, EmptyFieldsException*/ {
        if(surname.isEmpty()){
            /*throw new EmptyFieldsException();*/
            System.out.println("Surname field empty.");
        } else if(isNotValidLength(surname)){
            /*throw new InvalidUserInfoException();*/
            System.out.println("Surname field invalid.");
        }
    }

    private static boolean isValidBirth(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate ld = LocalDate.parse(value, formatter);
            String result = ld.format(formatter);
            return result.equals(value);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return false;
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

}
