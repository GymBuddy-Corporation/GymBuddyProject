import beans.ExerciseForWorkoutRoutineBean;
import beans.ExerciseStatusBean;
import beans.WorkoutDayBean;
import viewone.beans.WorkoutRoutineBean1;
import controllers.SatisfyWorkoutRequestsController;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAddExercise {

    ExerciseForWorkoutRoutineBean exerciseToAdd = new ExerciseForWorkoutRoutineBean("Tricep Pushdown", ExerciseStatusBean.ACTIVE, "monday", 10, 4, "01:30");
    ExerciseForWorkoutRoutineBean ex2 = new ExerciseForWorkoutRoutineBean("Dips", ExerciseStatusBean.ACTIVE, "tuesday", 8, 5, "03:00");
    ExerciseForWorkoutRoutineBean ex3 = new ExerciseForWorkoutRoutineBean("Trazioni", ExerciseStatusBean.ACTIVE, "monday", 15, 2, "02:00");
    ExerciseForWorkoutRoutineBean ex4 = new ExerciseForWorkoutRoutineBean("Bench Press", ExerciseStatusBean.ACTIVE, "monday", 3, 6, "04:00");
    WorkoutDayBean monday = new WorkoutDayBean("monday");
    WorkoutDayBean tuesday = new WorkoutDayBean("tuesday");
    WorkoutRoutineBean1 workoutRoutineBean = new WorkoutRoutineBean1();

    @org.junit.jupiter.api.Test
    public void TestAddExerciseToWorkoutDay() {
        SatisfyWorkoutRequestsController controller = new SatisfyWorkoutRequestsController();

        tuesday.getExerciseList().add(ex2);
        monday.getExerciseList().add(ex3);
        monday.getExerciseList().add(ex4);

        workoutRoutineBean.addWorkoutDayBean(monday);
        workoutRoutineBean.addWorkoutDayBean(tuesday);

        //controller.addExerciseToWorkoutDay(exerciseToAdd, workoutRoutineBean);
        boolean flag = false;
        int ver = 0;
        for(WorkoutDayBean workoutDay : workoutRoutineBean.getWorkoutDayList()){
            for (ExerciseForWorkoutRoutineBean exe : workoutDay.getExerciseBeanList()){
                System.out.println(exe.getName());
                if (exe.getName().equals(exerciseToAdd.getName())){
                     flag=true;
                     ver++;
                 }
            }
        }
        if(ver!=1){
            flag = false;
        }
        assertTrue(flag);
    }
}
