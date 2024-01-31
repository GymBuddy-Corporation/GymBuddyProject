package beans;

import exceptions.dataException.DataFieldException;
import exceptions.dataException.TyperEnumerations.FieldsEnum;
import exceptions.dataException.TyperEnumerations.ProblemEnum;

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

    public void addExerciseBean(ExerciseForWorkoutRoutineBean exerciseBean) throws DataFieldException{
        for (ExerciseForWorkoutRoutineBean existingExercise : this.getExerciseList()) {
            if (existingExercise.getName().equals(exerciseBean.getName())) {
                throw new DataFieldException(FieldsEnum.EXERCISE, ProblemEnum.IS_ALREADY_ADDED);
            }
        }
        this.exerciseBeanList.add(exerciseBean);
    }

    public void removeExerciseBean(ExerciseForWorkoutRoutineBean exerciseBean) {
        exerciseBeanList.remove(exerciseBean);
    }
}
