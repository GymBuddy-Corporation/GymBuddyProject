package exceptions;


public class DBUnrreachableException extends CostumException
{
    public DBUnrreachableException(){
        super("We are currently having problem with our db");
    }
}
