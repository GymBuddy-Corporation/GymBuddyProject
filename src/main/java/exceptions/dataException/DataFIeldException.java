package exceptions.dataException;

import exceptions.CostumException;

public class DataFIeldException extends CostumException {
    public DataFIeldException(String field,String reason) {
        super("Error with "+field +"field:"+reason);
    }
    public DataFIeldException(String field,Throwable e, String reason){
        super("Error with "+field+" field:"+reason,e);
    }
    public DataFIeldException(String field){
        super("Error with "+field+" field!");
    }
    public DataFIeldException(String field,Throwable e){
        super("Error with "+field+" field!",e);
    }
}
