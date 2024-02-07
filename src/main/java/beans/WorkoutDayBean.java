package beans;

import exceptions.dataexception.DataFieldException;
import exceptions.dataexception.typeenumerations.FieldsEnum;
import exceptions.dataexception.typeenumerations.ProblemEnum;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDayBean {

    private final String name;

    List<ExerciseForWorkoutRoutineBean> exerciseBeanList;
    public WorkoutDayBean(String name){
        this.name = name;
        this.exerciseBeanList = new ArrayList<>();
    }

    public WorkoutDayBean(String name, List<ExerciseForWorkoutRoutineBean> exerciseBeanList){
        this.name = name;
        this.exerciseBeanList = exerciseBeanList;
    }

    public String getName(){
        return this.name;
    }

    public List<ExerciseForWorkoutRoutineBean> getExerciseBeanList() {
        return exerciseBeanList;
    }

    public void setExerciseBeanList(List<ExerciseForWorkoutRoutineBean> exerciseBeanList) {
        this.exerciseBeanList = exerciseBeanList;
    }

    public void addExerciseBean(ExerciseForWorkoutRoutineBean exerciseBean) throws DataFieldException{
        for (ExerciseForWorkoutRoutineBean existingExercise : this.getExerciseList()) {
            if (existingExercise.getName().equals(exerciseBean.getName())) {
                throw new DataFieldException(FieldsEnum.EXERCISE, ProblemEnum.IS_ALREADY_ADDED);
            }
        }
        this.exerciseBeanList.add(exerciseBean);
    }

    public List<ExerciseForWorkoutRoutineBean> getExerciseList() {
        return exerciseBeanList;
    }

    public void removeExerciseBean(ExerciseForWorkoutRoutineBean exerciseBean) {
        exerciseBeanList.remove(exerciseBean);
    }
}
