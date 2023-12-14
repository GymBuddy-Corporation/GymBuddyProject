package model;

import beans.ExerciseForWorkoutRoutineBean;
import engineering.Observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WorkoutDayObserver extends WorkoutDay implements Observer, Serializable {
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

    @Override
    public void update(String changedExerciseName) {
        /*for (int i = exerciseList.size() - 1; i >= 0; i--) {
            ExerciseForWorkoutRoutine exercise = exerciseList.get(i);
            if (exercise.getName().equals(changedExerciseName)) {
                //TODO exercise.setStatus(status);
            }
        }*/
        /*if (changedExerciseName.getStatus() == ExerciseStatus.SUSPENDED) {
            findAndUpdate(changedExerciseName, ExerciseStatus.SUSPENDED);
        } else if (changedExerciseName.getStatus() == ExerciseStatus.ACTIVE) {
            findAndUpdate(changedExerciseName, ExerciseStatus.ACTIVE);
        }*/
    }
}