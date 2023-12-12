package model;

import beans.ExerciseForWorkoutRoutineBean;
import engineering.Observer;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDayObserver extends WorkoutDay implements Observer {
    //private final List<Exercise> exercises;
    protected WorkoutRoutine workoutRoutine;

    public WorkoutDayObserver(String day, List<ExerciseForWorkoutRoutine> exerciseList, WorkoutRoutine workoutRoutine) {
        super();
        addAllExercise(exerciseList);
        this.day = day;
        this.workoutRoutine = workoutRoutine;
    }

    public WorkoutDayObserver(String day, ExerciseForWorkoutRoutine exercise, WorkoutRoutine workoutRoutine) {
        super();
        addExercise(exercise);
        this.day = day;
        this.workoutRoutine = workoutRoutine;
    }

    public void findAndUpdate(Exercise changedExercise, ExerciseStatus status){
        for (int i = exerciseList.size() - 1; i >= 0; i--) {
            ExerciseForWorkoutRoutine exercise = exerciseList.get(i);
            if (exercise.getName().equals(changedExercise.getName())) {
                exercise.setStatus(status);
            }
        }
    }


    @Override
    public void update(Exercise changedExercise) {
        if (changedExercise.getStatus() == ExerciseStatus.SUSPENDED) {
            findAndUpdate(changedExercise, ExerciseStatus.SUSPENDED);
        } else if (changedExercise.getStatus() == ExerciseStatus.ACTIVE) {
            findAndUpdate(changedExercise, ExerciseStatus.ACTIVE);
        }
    }
}