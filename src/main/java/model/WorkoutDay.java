package model;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDay {
    private String workoutRoutineName;
    protected String day;
    protected transient List<ExerciseForWorkoutRoutine> exerciseList;

    public WorkoutDay(String day, String workoutRoutine, List<ExerciseForWorkoutRoutine> exerciseList){
        this.day = day;
        this.workoutRoutineName = workoutRoutine;
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

    public void addExercise(ExerciseForWorkoutRoutine exercise){
        this.exerciseList.add(exercise);
    }

    public void addAllExercise(List<ExerciseForWorkoutRoutine> exerciseList){
        this.exerciseList = exerciseList;
    }

    public String getDay() {
        return day;
    }

    public String getWorkoutRoutineName() {
        return workoutRoutineName;
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
