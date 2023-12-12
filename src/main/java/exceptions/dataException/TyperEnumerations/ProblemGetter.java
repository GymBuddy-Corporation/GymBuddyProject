package exceptions.dataException.TyperEnumerations;

public class ProblemGetter {
    public static String getProblem(ProblemEnum field){
        return switch (field) {
            case ProblemEnum.empty -> "empty field is not  valid";
            case ProblemEnum.isNull -> "null filed is not valid";
            case ProblemEnum.notValid -> "this value is not valid";
            default -> "error";
        };
    }//piccolo cambiamento
}
