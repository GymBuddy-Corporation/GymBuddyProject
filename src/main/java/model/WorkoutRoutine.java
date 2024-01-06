package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WorkoutRoutine {

    private String name;
    private final LocalDateTime activateDate;
    private String comment;
    private List<WorkoutDay> workoutDayList;

    public WorkoutRoutine(String name, String comment){
        this();
        this.comment = comment;
        this.name = name;
        this.workoutDayList = new ArrayList<>();
    }
    public WorkoutRoutine(){
        this.activateDate = LocalDateTime.now();
    }

    public List<WorkoutDay> getWorkoutDayList() {
        return workoutDayList;
    }

    public LocalDateTime getActivateDate() {
        return activateDate;
    }

    public void addAllWorkoutDays(List<WorkoutDay> workoutDayList) {
        this.workoutDayList = new ArrayList<>();
        for (WorkoutDay workoutDay : workoutDayList) {
            WorkoutDay newWorkoutDay = new WorkoutDay(workoutDay.getDay());
            newWorkoutDay.addAllExercise(workoutDay.getExerciseList());
            addWorkoutDay(newWorkoutDay);
        }

    }

    public void addWorkoutDay(WorkoutDay workoutDay) {
        workoutDayList.add(workoutDay);
    }

    public void removeWorkoutDay(WorkoutDay workoutDay) {
        workoutDayList.remove(workoutDay);
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

}
