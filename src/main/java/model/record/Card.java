package model.record;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.Objects;

public final class Card implements Serializable {
    private final String cardNumber;
    private final String name;
    private final String surname;
    private final YearMonth cardExpirationDate;

    public Card(String cardNumber, String name, String surname, YearMonth cardExpirationDate) {
        this.cardNumber = cardNumber;
        this.name = name;
        this.surname = surname;
        this.cardExpirationDate = cardExpirationDate;
    }


    public String cardNumber() {
        return cardNumber;
    }

    public String name() {
        return name;
    }

    public String surname() {
        return surname;
    }

    public YearMonth cardExpirationDate() {
        return cardExpirationDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, name, surname, cardExpirationDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Card) obj;
        return Objects.equals(this.cardNumber, that.cardNumber) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.surname, that.surname) &&
                Objects.equals(this.cardExpirationDate, that.cardExpirationDate);
    }

    @Override
    public String toString() {
        return "Card[" +
                "cardNumber=" + cardNumber + ", " +
                "name=" + name + ", " +
                "surname=" + surname + ", " +
                "cardExpirationDate=" + cardExpirationDate + ']';
    }
}
