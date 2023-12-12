package exceptions.dataException.TyperEnumerations;

public class FieldGetter {
    public static String getField(FieldsEnum field){
        return switch (field) {
            case FieldsEnum.email -> "email";
            case FieldsEnum.password -> "password";
            default -> "error";
        };
    }
}
