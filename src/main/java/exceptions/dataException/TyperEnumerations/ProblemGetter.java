package exceptions.dataException.TyperEnumerations;

public class ProblemGetter {
    public static String getProblem(ProblemGetter field){
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
