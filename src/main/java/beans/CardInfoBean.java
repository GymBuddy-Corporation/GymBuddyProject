package beans;

import exceptions.dataexception.DataFieldException;
import exceptions.dataexception.TyperEnumerations.FieldsEnum;
import exceptions.dataexception.TyperEnumerations.ProblemEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardInfoBean {
    private final String cardNumber;
    private final String monthOfExpiration;
    private final String yearOfExpiration;
    private final String nameOfOwner;
    private final String surnameOfOwenr;

    public CardInfoBean(String cardNumber,String monthOfExpiration,String yearOfExpiration,String nameOfOwner,String surnameOfOwenr) throws DataFieldException {
        Pattern pattern = Pattern.compile("^ *\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d *$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(cardNumber);
        if (!matcher.matches()) throw new DataFieldException(FieldsEnum.CREDIT_NUMBER, ProblemEnum.NOT_VALID);
        pattern = Pattern.compile("^ *(0?[1-9]|1[0-2]) *$", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(monthOfExpiration);
        if (!matcher.matches()) throw new DataFieldException(FieldsEnum.CREDIT_MONTH, ProblemEnum.NOT_VALID);
        pattern = Pattern.compile("^ *[1-2]\\d\\d\\d *$", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(yearOfExpiration);
        if (!matcher.matches()) throw new DataFieldException(FieldsEnum.CREDIT_YEAR, ProblemEnum.NOT_VALID);
        this.cardNumber = cardNumber;
        this.monthOfExpiration = monthOfExpiration;
        this.yearOfExpiration = yearOfExpiration;
        this.nameOfOwner = nameOfOwner;
        this.surnameOfOwenr = surnameOfOwenr;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getYearOfExpiration() {
        return yearOfExpiration;
    }

    public String getNameOfOwner() {
        return nameOfOwner;
    }

    public String getMonthOfExpiration() {
        return monthOfExpiration;
    }

    public String getSurnameOfOwenr() {
        return surnameOfOwenr;
    }
}
