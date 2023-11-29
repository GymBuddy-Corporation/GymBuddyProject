package exceptions;
public class InvalidExerciseFeaturesException extends InvalidDataException {

    public InvalidExerciseFeaturesException() {
        super(
                "ERROR DETECTED",
                "Invalid exercise features inserted.",
                "Please check again if sets, repetitions or rest is incorrect."
        );
    }
}
