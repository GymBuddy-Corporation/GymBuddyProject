package exceptions;
public class InvalidCardInfoException extends InvalidDataException {

    public InvalidCardInfoException() {
        super(
                "ERROR DETECTED",
                "Invalid inserted card info.",
                "Please chack again if card number or date is correct."
        );
    }
}
