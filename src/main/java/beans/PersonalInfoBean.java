package beans;

import exceptions.dataException.DataFieldException;
import exceptions.dataException.TyperEnumerations.FieldsEnum;
import exceptions.dataException.TyperEnumerations.ProblemEnum;

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

    private void checkName(String name) throws DataFieldException  {
        if(name.isEmpty()){
            throw new DataFieldException(FieldsEnum.Password, ProblemEnum.Empty);
        } else if(isNotValidLength(name)) {
            throw new DataFieldException(FieldsEnum.Password,ProblemEnum.NotValid);
        }
    }

    private LocalDate checkBirth(String birth) throws DataFieldException{
        if(birth.isEmpty()){
            throw new DataFieldException(FieldsEnum.Date,ProblemEnum.Empty);
        } else if(isValidBirth(birth)) {
            return LocalDate.parse(birth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }else {
            throw new DataFieldException(FieldsEnum.Date,ProblemEnum.NotValid);
        }
    }

    private boolean isNotValidLength(String str) {
        return str.length() > 45;
    }

    private void checkFc(String fc) throws DataFieldException {
        if(fc.isEmpty()){
            throw new DataFieldException(FieldsEnum.FC,ProblemEnum.Empty);
        } else if(!isValidFc(fc)) {
            throw new DataFieldException(FieldsEnum.FC,ProblemEnum.Empty);
        }
    }


    private boolean isValidFc(String fc) {
        return Pattern.matches("^([A-Z]{6}[\\dLMNPQRSTUV]{2}[ABCDEHLMPRST][\\dLMNPQRSTUV]{2}[A-Z][\\dLMNPQRSTUV]{3}[A-Z])$|(\\d{11})$",fc);
    }

    private void checkSurname(String surname) throws DataFieldException {
        if(surname.isEmpty()){
            throw new DataFieldException(FieldsEnum.Name,ProblemEnum.Empty);
        } else if(isNotValidLength(surname)){
            throw new DataFieldException(FieldsEnum.Name,ProblemEnum.NotValid);
        }
    }

    private static boolean isValidBirth(String value) throws DataFieldException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate ld = LocalDate.parse(value, formatter);
            String result = ld.format(formatter);
            return result.equals(value);
        } catch (DateTimeParseException e) {
            throw new DataFieldException(FieldsEnum.Date,ProblemEnum.NotValid);
        }
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
