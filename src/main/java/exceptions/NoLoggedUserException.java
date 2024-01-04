package exceptions;

public class NoLoggedUserException extends CostumException{
    public NoLoggedUserException(){
        super("No User logged");
    }
}
