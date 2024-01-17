package beans;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDayBean {

    private String name;

    List<ExerciseForWorkoutRoutineBean> exerciseBeanList;

    public WorkoutDayBean(){
        this.exerciseBeanList = new ArrayList<>();
    }

    public WorkoutDayBean(String name){
        this.name = name;
        this.exerciseBeanList = new ArrayList<>();
    }

    public WorkoutDayBean(String name, List<ExerciseForWorkoutRoutineBean> exerciseBeanList){
        this.name = name;
        this.exerciseBeanList = exerciseBeanList;
    }
    public List<ExerciseForWorkoutRoutineBean> getExerciseList() {
        return exerciseBeanList;
    }

    public void setExerciseBeanList(List<ExerciseForWorkoutRoutineBean> exerciseBeanList) {
        this.exerciseBeanList = exerciseBeanList;
    }

    public String getName(){
        return this.name;
    }

    public List<ExerciseForWorkoutRoutineBean> getExerciseBeanList() {
        return exerciseBeanList;
    }

    public void addExerciseBean(ExerciseForWorkoutRoutineBean exerciseBean) {
        exerciseBeanList.add(exerciseBean);
    }

    public void removeExerciseBean(ExerciseForWorkoutRoutineBean exerciseBean) {
        exerciseBeanList.remove(exerciseBean);
    }
}
