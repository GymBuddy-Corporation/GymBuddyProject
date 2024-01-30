package exceptions.dataException.TyperEnumerations;

public class ProblemGetter {
    public static String getProblem(ProblemEnum field){
        return switch (field) {
            case ProblemEnum.Empty -> "empty field is not valid";
            case ProblemEnum.IsNull-> "null filed is not valid";
            case ProblemEnum.NotValid -> "this value is not valid";
            case ProblemEnum.IsAlreadyAdded -> "this value is is already added";

            default -> "error";
        };
    }
}
