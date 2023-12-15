package exceptions;

public class UserCastException extends CostumException{
    //TODO valuta se togliere
    public UserCastException()
    {
        super("Error while trying to load user!");
    }
    public UserCastException(Throwable e) {
        super("Error while trying to load user!", e);
    }
}
