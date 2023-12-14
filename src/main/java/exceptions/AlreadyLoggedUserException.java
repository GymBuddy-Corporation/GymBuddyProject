package exceptions;

public class AlreadyLoggedUserException extends CostumException{
    public AlreadyLoggedUserException(){
        super("An user is alredy logged");
    }
}
