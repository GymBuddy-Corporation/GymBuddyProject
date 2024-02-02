package exceptions;

public class EmptySearchException extends CostumException{
    public EmptySearchException(){
        super("There is no exercise for this search");    }
}
