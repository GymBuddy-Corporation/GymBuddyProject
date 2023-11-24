package controllers;

import beans.*;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import model.Exercise;
import model.WorkoutRoutine;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class SatisfyWorkoutRequestsController implements Initializable {

    //TODO da risistemare; occhio che da un punto di vista di svolgimento di codice questa classe deve estendere la classe

    //Tutti sti valori bean diventeranno poi model

    private final WorkoutRoutineBean workoutRoutine;
    private final String trainer;
    private List<Exercise> exerciseCatalogue;

    public List<ExerciseBean> getGymExercises() {
        return getExerciseBeanList(exerciseCatalogue);
    }

    public SatisfyWorkoutRequestsController() {
        workoutRoutine = new WorkoutRoutineBean();
        trainer = "Personal Trainer Loggato";
        exerciseCatalogue  = new ArrayList<>() ;
    }

    public void setExerciseCatalogue(List<Exercise> exerciseCatalogue){
        this.exerciseCatalogue=exerciseCatalogue;
    }

    public SatisfyWorkoutRequestsController(String trainer, List<Exercise> exerciseCatalogue) {
        workoutRoutine = new WorkoutRoutineBean();
        this.trainer = trainer;
        this.exerciseCatalogue = exerciseCatalogue;
    }

    public void addExerciseToWorkoutDay(ExerciseForWorkoutRoutineBean exercise, int repetitions, int sets, String rest, ListView<ExerciseForWorkoutRoutineBean> RoutineExerciselist)  {
        RoutineExerciselist.getItems().add(exercise);

        // Retrieve rest, sets, and repetitions from the ExerciseForWorkoutRoutineBean instance
        String exerciseRest = exercise.getRest();
        int exerciseSets = exercise.getSets();
        int exerciseRepetitions = exercise.getRepetitions();
        String exerciseName = exercise.getExercise().getName();
        String exerciseDay = exercise.getDay();

        // Check if the WorkoutDayBean for the current day already exists
        Optional<WorkoutDayBean> existingWorkoutDay = workoutRoutine.getWorkoutDayList().stream()
                .filter(workoutDay -> workoutDay.getName().equals(exercise.getDay()))
                .findFirst();

        if (existingWorkoutDay.isPresent()) {
            existingWorkoutDay.get().addExerciseBean(exercise);
            // Print info when WorkoutDayBean exists
            System.out.println("WorkoutDayBean already exists for day: " + exerciseDay +
                    ", Exercise: " + exerciseName +
                    ", Repetitions: " + exerciseRepetitions +
                    ", Sets: " + exerciseSets +
                    ", Rest: " + exerciseRest);
        } else {
            // If WorkoutDayBean doesn't exist, create a new one and add the exercise to it
            WorkoutDayBean newWorkoutDay = new WorkoutDayBean(exercise.getDay());
            newWorkoutDay.addExerciseBean(exercise);
            workoutRoutine.addWorkoutDayBean(newWorkoutDay);
            // Print info when creating a new WorkoutDayBean
            System.out.println("Created new WorkoutDayBean for day: " + exerciseDay +
                    ", Exercise: " + exerciseName +
                    ", Repetitions: " + exerciseRepetitions +
                    ", Sets: " + exerciseSets +
                    ", Rest: " + exerciseRest);
        }
        // Additional code if needed
    }

    public void changeExerciseStatus(){

    }




    public void submitRoutine() {

        for (WorkoutDayBean workoutDay : workoutRoutine.getWorkoutDayList()) {
            String dayName = workoutDay.getName();

            // Iterate through each ExerciseForWorkoutRoutineBean in the WorkoutDayBean
            for (ExerciseForWorkoutRoutineBean exercisePrint : workoutDay.getExerciseList()) {
                ExerciseBean exerciseName = exercisePrint.getExercise();
                int repetitionsP = exercisePrint.getRepetitions();
                int setsP = exercisePrint.getSets();
                String restP = exercisePrint.getRest();

                System.out.println("Day: " + dayName +
                        ", Exercise: " + exerciseName.getName() +
                        ", Repetitions: " + repetitionsP +
                        ", Sets: " + setsP +
                        ", Rest: " + restP);
            }
        }
    }

    public List<ExerciseBean> searchExercise(SearchBean searchBean) {
        System.out.println(searchBean.getName());
        List<Exercise> exerciseList = new ArrayList<>();
        for(Exercise exercise: exerciseCatalogue) {
            System.out.println(exercise.getName());
            if((exercise.getName().toLowerCase()).contains(searchBean.getName().toLowerCase())) {
                exerciseList.add(exercise);
            }
        }
        return getExerciseBeanList(exerciseList);
    }

    @NotNull
    private List<ExerciseBean> getExerciseBeanList(List<Exercise> exerciseList) {
        List<ExerciseBean> exerciseBeanList = new ArrayList<>();
        for(Exercise exercise: exerciseList){
            exerciseBeanList.add(new ExerciseBean(exercise.getName()));
        }
        return exerciseBeanList;
    }



    private WorkoutDayBean getWorkoutDay(WorkoutDayBean day) {
        //ragiona solo con le bean
        for(WorkoutDayBean workoutDay: workoutRoutine.getWorkoutDayList()){
            if(Objects.equals(workoutDay, day)) {
                return workoutDay;
            }
        }
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //comment so to not have code smell
    }
}
