package exceptions;

public class InvalidExerciseFeaturesException extends CostumException{
    public InvalidExerciseFeaturesException(String message) {
        super(message);
    }
    public InvalidExerciseFeaturesException(String message,Throwable e) {
        super(message,e);
    }
}
