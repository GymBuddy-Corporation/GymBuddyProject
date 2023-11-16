package beans;

import java.util.ArrayList;
import java.util.List;

public class WorkoutRoutineBean {

    private int id;
    private String name;
    private List<WorkoutDayBean> workoutDayList;

    public WorkoutRoutineBean(int id, String name) {
        this.id = id;
        this.workoutDayList = new ArrayList<>();
        this.name = name;
    }

    public WorkoutRoutineBean() {
        this.workoutDayList = new ArrayList<>();
    }

    public List<WorkoutDayBean> getWorkoutDayList() {return workoutDayList;}

    public void setWorkoutDayList(List<WorkoutDayBean> workoutDayList) {
        this.workoutDayList = workoutDayList;
    }

    public int getId() {
        return id;
    }

    public void addWorkoutDayBean(WorkoutDayBean workoutDayBean) {
        workoutDayList.add(workoutDayBean);
    }


    public String getName() {return name;}

    public void setName(String name) {this.name = name;}
}
