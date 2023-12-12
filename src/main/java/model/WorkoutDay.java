package model;

import beans.ExerciseForWorkoutRoutineBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorkoutDay implements Serializable {
    private String workoutRoutine;
    protected String day;
    protected transient List<ExerciseForWorkoutRoutine> exerciseList;

    public WorkoutDay(String day, List<ExerciseForWorkoutRoutine> exerciseList, String workoutRoutine){
        this.day = day;
        this.workoutRoutine = workoutRoutine;
        addAllExercise(exerciseList);
    }

    public WorkoutDay(String day) {
        this();
        this.day = day;
    }

    public WorkoutDay() {
        exerciseList = new ArrayList<>();
    }

    public List<ExerciseForWorkoutRoutine> getExerciseList(){
        return exerciseList;
    }

    public void addExercise(ExerciseForWorkoutRoutine exercise) {
        this.exerciseList.add(exercise);
    }

    public void addAllExercise(List<ExerciseForWorkoutRoutine> exerciseList){
        this.exerciseList = exerciseList;
    }

    public String getDay() {
        return day;
    }

   /* public void removeExercise(String name, String info) {
        for(Exercise exercise: exerciseList) {
            if(Objects.equals(exercise.getName(), name) && Objects.equals(exercise.getInfo(), info)) {
                exerciseList.remove(exercise);
                return;
            }
        }
    }*/
}
