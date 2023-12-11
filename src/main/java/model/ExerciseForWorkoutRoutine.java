package model;

import beans.ExerciseBean;
import exceptions.InvalidExerciseFeaturesException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExerciseForWorkoutRoutine {
    protected final String day;
    protected final String workoutRoutineName;
    private int repetitions;
    private int sets;
    private String rest;
    protected final Exercise exercise;

    public ExerciseForWorkoutRoutine(String day, Exercise exercise, int repetitions, int sets, String rest, String workoutRoutineName) {
        this.day = day;
        this.exercise = exercise;
        this.rest = rest;
        this.sets = sets;
        this.repetitions = repetitions;
        this.workoutRoutineName = workoutRoutineName;
    }

    public ExerciseForWorkoutRoutine(String day, Exercise exercise, String workoutRoutineName) {
        this.day = day;
        this.exercise = exercise;
        this.workoutRoutineName = workoutRoutineName;
    }

    public String getDay() {
        return day;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public int getRepetitions() {return repetitions;}

    public int getSets() {return sets;}

    public String getRest() {return rest;}

    public void setRest(String timeString) {
        this.rest = timeString;
    }
    public void setRepetitions(int reps) {this.repetitions = reps;}
    public void setSets(int sets) {this.sets = sets;}
}
