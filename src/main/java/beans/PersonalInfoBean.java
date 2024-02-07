package beans;

import exceptions.dataexception.DataFieldException;
import exceptions.dataexception.typeenumerations.FieldsEnum;
import exceptions.dataexception.typeenumerations.ProblemEnum;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class PersonalInfoBean {
    private final String name;
    private final String surname;
    private final LocalDate dateOfBirth;
    private final String fc; /*fc = fiscal code*/
    private final char gender;

    public PersonalInfoBean(String name, String surname, LocalDate dateOfBirth, String fc, char gender) {
        /*This is a constructor without syntax check and is used by controller*/
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.fc = fc;
        this.gender = gender;
    }

    public PersonalInfoBean(String name, String surname, String dateOfBirth, String fc, char gender) throws DataFieldException/* throws InvalidUserInfoException, InvalidFiscalCodeException, EmptyFieldsException, InvalidBirthException*/ {
        /*This is a constructor with syntax check and is used by view*/
        checkValue(name, surname, fc);
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = checkBirth(dateOfBirth);
        this.fc = fc;
        this.gender = gender;
    }

    private void checkValue(String name, String surname, String fiscalCode) throws DataFieldException  {
        checkName(name);
        checkSurname(surname);
        checkFc(fiscalCode);
    }

    private LocalDate checkBirth(String birth) throws DataFieldException{
        if(birth.isEmpty()){
            throw new DataFieldException(FieldsEnum.DATE,ProblemEnum.EMPTY);
        } else if(isValidBirth(birth)) {
            return LocalDate.parse(birth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }else {
            throw new DataFieldException(FieldsEnum.DATE,ProblemEnum.NOT_VALID);
        }
    }

    private void checkName(String name) throws DataFieldException  {
        if(name.isEmpty()){
            throw new DataFieldException(FieldsEnum.PASSWORD, ProblemEnum.EMPTY);
        } else if(isNotValidLength(name)) {
            throw new DataFieldException(FieldsEnum.PASSWORD,ProblemEnum.NOT_VALID);
        }
    }

    private void checkSurname(String surname) throws DataFieldException {
        if(surname.isEmpty()){
            throw new DataFieldException(FieldsEnum.NAME,ProblemEnum.EMPTY);
        } else if(isNotValidLength(surname)){
            throw new DataFieldException(FieldsEnum.NAME,ProblemEnum.NOT_VALID);
        }
    }

    private void checkFc(String fc) throws DataFieldException {
        if(fc.isEmpty()){
            throw new DataFieldException(FieldsEnum.FC,ProblemEnum.EMPTY);
        } else if(!isValidFc(fc)) {
            throw new DataFieldException(FieldsEnum.FC,ProblemEnum.NOT_VALID);
        }
    }

    private static boolean isValidBirth(String value) throws DataFieldException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate ld = LocalDate.parse(value, formatter);
            String result = ld.format(formatter);
            return result.equals(value);
        } catch (DateTimeParseException e) {
            throw new DataFieldException(FieldsEnum.DATE,ProblemEnum.NOT_VALID);
        }
    }

    private boolean isNotValidLength(String str) {
        return str.length() > 45;
    }

    private boolean isValidFc(String fc) {
        return Pattern.matches("^([A-Z]{6}[\\dLMNPQRSTUV]{2}[ABCDEHLMPRST][\\dLMNPQRSTUV]{2}[A-Z][\\dLMNPQRSTUV]{3}[A-Z])$|(\\d{11})$",fc);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getFc() {
        return fc;
    }

    public char getGender() {
        return gender;
    }
}
