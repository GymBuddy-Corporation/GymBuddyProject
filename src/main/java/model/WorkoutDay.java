package model;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDay {
    protected String day;
    protected  List<ExerciseForWorkoutRoutine> exerciseList;
    private String workoutRoutineName;

    public WorkoutDay(String day, String workoutRoutine, List<ExerciseForWorkoutRoutine> exerciseList){
        this.day = day;
        this.workoutRoutineName = workoutRoutine;
        addAllExercise(exerciseList);
    }

    public void addAllExercise(List<ExerciseForWorkoutRoutine> exerciseList){
        this.exerciseList = exerciseList;
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

    public void addExercise(ExerciseForWorkoutRoutine exercise){
        this.exerciseList.add(exercise);
    }

    public String getDay() {
        return this.day;
    }

    public String getWorkoutRoutineName() {
        return workoutRoutineName;
    }
}
