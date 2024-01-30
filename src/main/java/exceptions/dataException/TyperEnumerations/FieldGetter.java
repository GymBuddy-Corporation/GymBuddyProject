package exceptions.dataException.TyperEnumerations;

public class FieldGetter {
    public static String getField(FieldsEnum field){
        return switch (field) {
            case FieldsEnum.Email -> "email";
            case FieldsEnum.Password -> "password";
            case FieldsEnum.Date -> "date";
            case FC -> "fiscal code";
            case Name -> "name";
            default -> "error";
        };
    }
}
