package beans;

import exceptions.SubmitRoutineException;

import java.util.ArrayList;
import java.util.List;

public class WorkoutRoutineBean {

    private AthleteBean athlete;
    private String name;
    private String comment;
    private final List<WorkoutDayBean> workoutDayList;

    public WorkoutRoutineBean() {
        this.workoutDayList = new ArrayList<>();
    }

    public List<WorkoutDayBean> getWorkoutDayList() {return workoutDayList;}

    public AthleteBean getAthlete() {
        return athlete;
    }

    public void addWorkoutDayBean(WorkoutDayBean workoutDayBean) {
        workoutDayList.add(workoutDayBean);
    }

    public WorkoutDayBean getWorkoutDay(String workoutDayName) {
        for (WorkoutDayBean day: workoutDayList){
             if(day.getName().equals(workoutDayName)){
                return day;
            }
        }
        return null;
    }

    public String getName() {return name;}

    public void setName(String name) throws SubmitRoutineException {
        if(name.isEmpty()){
            throw new SubmitRoutineException("Routine name field empty");
        } else if(name.length() >15){
            throw new SubmitRoutineException("Routine name filed is too long");
        } else {
            this.name = name;
        }
    }
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) throws SubmitRoutineException {
        if(comment.isEmpty()){
            throw new SubmitRoutineException("Routine comment field empty");
        } else if(comment.length() > 500){
            throw new SubmitRoutineException("Routine name filed is too long");
        }else {
            this.comment = comment;
        }
    }

    public void setAthlete(AthleteBean athlete) {
        this.athlete = athlete;
    }
}