package model;

import beans.ExerciseBean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExerciseForWorkoutRoutine extends Exercise{
    private final String day;
    private final String workoutRoutineName;
    private int repetitions;
    private int sets;
    private String rest;

    public ExerciseForWorkoutRoutine(String name, Gym gym, ExerciseStatus status, String day, int repetitions, int sets, String rest, String workoutRoutineName) {
        super(name, gym, status);
        this.day = day;
        this.rest = rest;
        this.sets = sets;
        this.repetitions = repetitions;
        this.workoutRoutineName = workoutRoutineName;
    }

    public ExerciseForWorkoutRoutine(String name, Gym gym, ExerciseStatus status, String day, String workoutRoutineName) {
        super(name, gym, status);
        this.day = day;
        this.workoutRoutineName = workoutRoutineName;
    }

    public String getDay() {
        return day;
    }


    public int getRepetitions() {return repetitions;}

    public int getSets() {return sets;}

    public String getRest() {return rest;}

    public void setRest(String timeString) {
        this.rest = timeString;
    }
    public void setRepetitions(int reps) {this.repetitions = reps;}
    public void setSets(int sets) {this.sets = sets;}

    public String getWorkoutRoutineName() {
        return workoutRoutineName;
    }
}
