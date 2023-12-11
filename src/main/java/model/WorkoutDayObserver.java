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

    private void detachFromObservables() {
        //TODO gestisci il detach
        /*for (Exercise exercise : exerciseList) {
            exercise.removeObserver(this);
        }*/
    }

    @Override
    public void update(Exercise changedExercise) {
        if (changedExercise.getStatus() == ExerciseStatus.SUSPENDED) {
            // Remove ExerciseForWorkoutRoutine instances for exercises with SUSPENDED status
            for (int i = exerciseList.size() - 1; i >= 0; i--) {
                String exercise = exerciseList.get(i).getExercise().getName();
                if (exercise.equals(changedExercise.getName())) {
                    exerciseList.remove(i);
                }
            }

            // If no exercises are left, detach from observables and remove the WorkoutDay
            if (exerciseList.isEmpty()) {
                detachFromObservables();
                workoutRoutine.removeWorkoutDay(this);
            }
        }
    }
}