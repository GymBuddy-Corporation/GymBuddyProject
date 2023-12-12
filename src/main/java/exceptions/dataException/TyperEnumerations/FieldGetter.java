package exceptions.dataException.TyperEnumerations;

public class FieldGetter {
    public static String getField(FieldsEnum field){
            switch (field){
                case FieldsEnum.email :
                    return "email";
                case FieldsEnum.password:
                    return "password";
                default:
                    return "error";
            }
    }
}
