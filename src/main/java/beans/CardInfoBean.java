package beans;

public class CardInfoBean {
    public String getCardNumber() {
        return cardNumber;
    }

    private final String cardNumber;

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

    private final String monthOfExpiration;
    private final String yearOfExpiration;

    private final String nameOfOwner;
    private final String surnameOfOwenr;
    public CardInfoBean(String cardNumber,String monthOfExpiration,String yearOfExpiration,String nameOfOwner,String surnameOfOwenr){
        this.cardNumber = cardNumber;
        this.monthOfExpiration = monthOfExpiration;
        this.yearOfExpiration = yearOfExpiration;
        this.nameOfOwner = nameOfOwner;
        this.surnameOfOwenr = surnameOfOwenr;
    }
}
