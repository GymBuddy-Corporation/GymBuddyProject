package exceptions.dataException.TyperEnumerations;

public class FieldGetter {
    public static String getField(FieldsEnum field){
        return switch (field) {
            case FieldsEnum.EMAIL -> "email";
            case FieldsEnum.PASSWORD -> "password";
            case FieldsEnum.DATE -> "date";
            case FieldsEnum.FC -> "fiscal code";
            case FieldsEnum.NAME -> "name";
            case FieldsEnum.EXERCISE_REST -> "exercise rest";
            case FieldsEnum.EXERCISE_REPS -> "exercise repetitions";
            case FieldsEnum.EXERCISE_SETS -> "exercise sets";
            case FieldsEnum.EXERCISE -> "exercise";
            case FieldsEnum.USERNAME -> "username";
            case FieldsEnum.INFO -> "info";
            case FieldsEnum.ROUTINE_NAME -> "routines' name";
            case FieldsEnum.ROUTINE_COMMENT -> "routines' comment";
            default -> "error";
        };
    }
}
