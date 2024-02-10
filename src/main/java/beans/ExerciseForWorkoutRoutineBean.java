package beans;


import exceptions.dataexception.DataFieldException;
import exceptions.dataexception.typeenumerations.FieldsEnum;
import exceptions.dataexception.typeenumerations.ProblemEnum;
import model.ExerciseStatus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExerciseForWorkoutRoutineBean extends ExerciseBean{
    private final String day;
    private int repetitions;
    private int sets;
    private String rest;

    public ExerciseForWorkoutRoutineBean(String name, ExerciseStatus statusExercise, String day, int repetitions, int sets, String rest) {
        super(name, statusExercise);
        this.day = day;
        this.rest = rest;
        this.sets = sets;
        this.repetitions = repetitions;
    }

    public ExerciseForWorkoutRoutineBean(String name, ExerciseStatus statusExercise, String day) {
        super(name, statusExercise);
        this.day = day;
    }

    public ExerciseForWorkoutRoutineBean(String name, String day) {
        super(name);
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public int getRepetitions() {return repetitions;}

    public void setRepetitions(int reps) throws DataFieldException {
        if(reps>0){
            this.repetitions = reps;
        } else if (reps == 0){
            throw new DataFieldException(FieldsEnum.EXERCISE_REPS, ProblemEnum.IS_NULL);
        } else {
            throw new DataFieldException(FieldsEnum.EXERCISE_REPS, ProblemEnum.NOT_VALID);
        }
    }

    public int getSets() {return sets;}

    public void setSets(int sets) throws DataFieldException{
        if(sets>0){
            this.sets = sets;
        } else if (sets == 0){
            throw new DataFieldException(FieldsEnum.EXERCISE_SETS, ProblemEnum.IS_NULL);
        } else {
            throw new DataFieldException(FieldsEnum.EXERCISE_SETS, ProblemEnum.NOT_VALID);
        }
    }

    public String getRest() {return rest;}

    public void setRest(String timeString) throws DataFieldException{
        if(isValidTime(timeString)) {
            this.rest = timeString;
        } else if (timeString==null){
            throw new DataFieldException(FieldsEnum.EXERCISE_REST, ProblemEnum.IS_NULL);
        }else {
            throw new DataFieldException(FieldsEnum.EXERCISE_REST, ProblemEnum.NOT_VALID);
        }
    }

    private static boolean isValidTime(String timeString) {
        if (timeString == null) {
            return false;
        }

        String timeRegex = "([01]?\\d|2[0-3]):[0-5]\\d";
        Pattern pattern = Pattern.compile(timeRegex);
        Matcher matcher = pattern.matcher(timeString);
        return matcher.matches();
    }
}
