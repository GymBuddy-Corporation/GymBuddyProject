package beans;

import java.util.List;

public interface WorkoutRoutineBean {
    List<WorkoutDayBean> getWorkoutDayList();
    AthleteBean getAthlete();
    String getName();
}
