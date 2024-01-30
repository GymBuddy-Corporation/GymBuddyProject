package exceptions;

import java.util.Collections;

public class DBUnrreachableException extends CostumException
{
    public DBUnrreachableException(){
        super("We are currently having problem with our db");
    }
}
