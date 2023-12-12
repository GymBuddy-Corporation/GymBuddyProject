package exceptions.dataException;

import exceptions.CostumException;
import exceptions.dataException.TyperEnumerations.FieldGetter;
import exceptions.dataException.TyperEnumerations.FieldsEnum;
import exceptions.dataException.TyperEnumerations.ProblemEnum;
import exceptions.dataException.TyperEnumerations.ProblemGetter;

public class DataFieldException extends CostumException {
    FieldsEnum dataType;
    ProblemEnum problem;
    public DataFieldException (FieldsEnum type, ProblemEnum problemType){
            super("Error with "+ FieldGetter.getField(type)+": "+ ProblemGetter.getProblem(problemType));
            dataType=type;
            problem=problemType;
    }
    public DataFieldException (FieldsEnum type, ProblemEnum problemType,Throwable e){
        super("Error with "+ FieldGetter.getField(type)+": "+ ProblemGetter.getProblem(problemType),e);
        dataType=type;
        problem=problemType;
    }
}
