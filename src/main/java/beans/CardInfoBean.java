package beans;

public class CardInfoBean {
    public String getCardNumber() {
        return cardNumber;
    }

    private String cardNumber;

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

    private String monthOfExpiration;
    private String yearOfExpiration;

    private String nameOfOwner;
    private String surnameOfOwenr;
    public CardInfoBean(String cardNumber,String monthOfExpiration,String yearOfExpiration,String nameOfOwner,String surnameOfOwenr){
        this.cardNumber = cardNumber;
        this.monthOfExpiration = monthOfExpiration;
        this.yearOfExpiration = yearOfExpiration;
        this.nameOfOwner = nameOfOwner;
        this.surnameOfOwenr = surnameOfOwenr;
    }
}
