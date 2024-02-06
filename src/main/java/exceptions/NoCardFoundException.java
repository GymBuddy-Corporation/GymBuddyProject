package exceptions;

public class NoCardFoundException extends CostumException{
    public NoCardFoundException(){
        super("No card Found saved");
    }
}
