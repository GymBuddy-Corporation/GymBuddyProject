package exceptions.dataException;

import exceptions.dataException.TyperEnumerations.FieldGetter;
import exceptions.dataException.TyperEnumerations.FieldsEnum;

public class PasswordFieldException extends DataFIeldException {
    public PasswordFieldException(){
        super(FieldGetter.getField(FieldsEnum.password));
    }
    public PasswordFieldException(String reason){
        super(FieldGetter.getField(FieldsEnum.password),reason);

    }
    public PasswordFieldException(Throwable e){
        super(FieldGetter.getField(FieldsEnum.password),e);
    }
    public PasswordFieldException(Throwable e,String reason){
        super(FieldGetter.getField(FieldsEnum.password),e,reason);
    }
}
