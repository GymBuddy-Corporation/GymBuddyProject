package exceptions.dataException;

import exceptions.dataException.TyperEnumerations.FieldGetter;
import exceptions.dataException.TyperEnumerations.FieldsEnum;

public class EmailFIeldException extends DataFIeldException {
    public EmailFIeldException(String reason) {
        super(FieldGetter.getField(FieldsEnum.email),reason);
    }
    public EmailFIeldException(Throwable e, String reason){
        super(FieldGetter.getField(FieldsEnum.email),e,reason);
    }
    public EmailFIeldException(){
        super(FieldGetter.getField(FieldsEnum.email));
    }
    public EmailFIeldException(Throwable e){
        super(FieldGetter.getField(FieldsEnum.email),e);
    }
}
