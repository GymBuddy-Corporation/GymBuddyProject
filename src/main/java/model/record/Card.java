package model.record;

import java.time.YearMonth;

public record Card(String cardNumber, String name,String surname,YearMonth cardExpirationDate) {}
