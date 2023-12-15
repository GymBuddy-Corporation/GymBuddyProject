package exceptions;

public class NoUserFoundException extends CostumException{
    public NoUserFoundException(){super("No user Found with this credentials");}
    public NoUserFoundException(Throwable e){super("No user Found with this credentials",e);}
}
