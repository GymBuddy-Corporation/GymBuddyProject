package exceptions.dataexception;

import exceptions.CostumException;
import exceptions.dataexception.typeenumerations.FieldGetter;
import exceptions.dataexception.typeenumerations.FieldsEnum;
import exceptions.dataexception.typeenumerations.ProblemEnum;
import exceptions.dataexception.typeenumerations.ProblemGetter;

public class DataFieldException extends CostumException {
    private final  FieldsEnum  dataType;
    private final ProblemEnum problem;
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
