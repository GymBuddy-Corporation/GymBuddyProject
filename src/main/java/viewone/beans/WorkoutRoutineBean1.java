package viewone.beans;

import beans.AthleteBean;
import beans.WorkoutDayBean;
import beans.WorkoutRoutineBean;

import java.util.ArrayList;
import java.util.List;

public class WorkoutRoutineBean1 implements WorkoutRoutineBean {

    private AthleteBean athlete;
    private String name;
    private String comment;
    private final List<WorkoutDayBean> workoutDayList;

    public WorkoutRoutineBean1() {
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

    public void setName(String name) {this.name = name;}

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setAthlete(AthleteBean athlete) {
        this.athlete = athlete;
    }
}
