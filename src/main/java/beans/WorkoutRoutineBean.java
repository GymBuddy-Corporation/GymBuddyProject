package beans;

import java.util.ArrayList;
import java.util.List;

public class WorkoutRoutineBean {

    private AthleteBean athlete;
    private String name;
    private String comment;
    private List<WorkoutDayBean> workoutDayList;

    public WorkoutRoutineBean(AthleteBean athlete) {
        this.athlete = athlete;
        this.workoutDayList = new ArrayList<>();
    }

    public WorkoutRoutineBean() {
        this.workoutDayList = new ArrayList<>();
    }

    public List<WorkoutDayBean> getWorkoutDayList() {return workoutDayList;}

    public void setWorkoutDayList(List<WorkoutDayBean> workoutDayList) {
        this.workoutDayList = workoutDayList;
    }

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
}
