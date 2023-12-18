import beans.*;
import controllers.SatisfyWorkoutRequestsController;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRemoveExercise {


    ExerciseForWorkoutRoutineBean exerciseToRemove = new ExerciseForWorkoutRoutineBean("Tricep Pushdown", ExerciseStatusBean.ACTIVE, "monday", 10, 4, "01:30");
    ExerciseForWorkoutRoutineBean ex2 = new ExerciseForWorkoutRoutineBean("Dips", ExerciseStatusBean.ACTIVE, "tuesday", 8, 5, "03:00");
    ExerciseForWorkoutRoutineBean ex3 = new ExerciseForWorkoutRoutineBean("Trazioni", ExerciseStatusBean.ACTIVE, "monday", 8, 5, "03:00");
    ExerciseForWorkoutRoutineBean ex4 = new ExerciseForWorkoutRoutineBean("Bench Press", ExerciseStatusBean.ACTIVE, "monday", 8, 5, "03:00");
    WorkoutDayBean monday = new WorkoutDayBean("monday");
    WorkoutDayBean tuesday = new WorkoutDayBean("tuesday");
    WorkoutRoutineBean workoutRoutineBean = new WorkoutRoutineBean();

    @org.junit.jupiter.api.Test
    public void TestRemoveExerciseToWorkoutDay() {
        SatisfyWorkoutRequestsController controller = new SatisfyWorkoutRequestsController();

        monday.getExerciseList().add(exerciseToRemove);
        tuesday.getExerciseList().add(ex2);
        monday.getExerciseList().add(ex3);
        monday.getExerciseList().add(ex4);

        workoutRoutineBean.addWorkoutDayBean(monday);
        workoutRoutineBean.addWorkoutDayBean(tuesday);
        System.out.println(workoutRoutineBean.getWorkoutDay(monday.getName()).getName());

        controller.removeExerciseToWorkoutDay(exerciseToRemove, workoutRoutineBean);
        boolean flag = true;
        for(WorkoutDayBean workoutDay : workoutRoutineBean.getWorkoutDayList()){
            for (ExerciseForWorkoutRoutineBean exe : workoutDay.getExerciseBeanList()){
                if (exe.getName().equals(exerciseToRemove.getName())){
                     flag=false;
                     break;
                 }
            }
        }
        assertTrue(flag);

    }
}
