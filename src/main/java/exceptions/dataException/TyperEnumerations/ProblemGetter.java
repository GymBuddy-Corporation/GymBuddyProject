package exceptions.dataException.TyperEnumerations;

public class ProblemGetter {
    public static String getProblem(ProblemEnum field){
        return switch (field) {
            case ProblemEnum.EMPTY -> "empty field is not valid";
            case ProblemEnum.IS_NULL -> "null filed is not valid";
            case ProblemEnum.NOT_VALID -> "this value is not valid";
            case ProblemEnum.IS_ALREADY_ADDED -> "this value is is already added";

            default -> "error";
        };
    }
}
