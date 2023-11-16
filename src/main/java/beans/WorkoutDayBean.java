package beans;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDayBean {

    private final String name;

    List<ExerciseBean> exerciseBeanList;


    public WorkoutDayBean(String name){
        this.name = name;
        this.exerciseBeanList = new ArrayList<>();
    }

    public List<ExerciseBean> getExerciseBeanList() {
        return exerciseBeanList;
    }

    public void setExerciseBeanList(List<ExerciseBean> exerciseBeanList) {
        this.exerciseBeanList = exerciseBeanList;
    }

    public String getName(){
        return this.name;
    }

    public void addExerciseBean(ExerciseBean exerciseBean) {
        exerciseBeanList.add(exerciseBean);
    }

}
