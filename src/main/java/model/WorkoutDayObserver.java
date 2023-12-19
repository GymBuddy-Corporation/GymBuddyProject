package model;

import beans.ExerciseForWorkoutRoutineBean;
import engineering.Observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WorkoutDayObserver extends WorkoutDay {
    /*private final List<ExerciseForWorkoutRoutine> exercises;
    protected WorkoutRoutine workoutRoutine;

    public WorkoutDayObserver(String day, List<ExerciseForWorkoutRoutine> exerciseList, WorkoutRoutine workoutRoutine) {
        this.day = day;
        this.workoutRoutine = workoutRoutine;
        this.exercises = exerciseList;
        for(ExerciseForWorkoutRoutine ex : exercises){
            ex.addObserver(this);
        }
    }

    public void findAndUpdate(Exercise changedExercise, ExerciseStatus status){

    }

    @Override
    public void addExercise(ExerciseForWorkoutRoutine exercise) {

    }

    @Override
    public void addAllExercise(List<ExerciseForWorkoutRoutine> exerciseList){
        for(ExerciseForWorkoutRoutine exercise: exerciseList) {
            addExercise(exercise);
        }
    }*/
}