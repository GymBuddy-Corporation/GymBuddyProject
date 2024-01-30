package exceptions.dataException.TyperEnumerations;

public class FieldGetter {
    public static String getField(FieldsEnum field){
        return switch (field) {
            case FieldsEnum.Email -> "email";
            case FieldsEnum.Password -> "password";
            case FieldsEnum.Date -> "date";
            case FieldsEnum.FC -> "fiscal code";
            case FieldsEnum.Name -> "name";
            case FieldsEnum.ExerciseRest -> "exercise rest";
            case FieldsEnum.ExerciseReps -> "exercise repetitions";
            case FieldsEnum.ExerciseSets -> "exercise sets";
            case FieldsEnum.Exercise -> "exercise";
            default -> "error";
        };
    }
}
