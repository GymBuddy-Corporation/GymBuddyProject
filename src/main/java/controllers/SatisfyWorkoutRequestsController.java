package controllers;

import beans.ExerciseBean;
import beans.ExerciseForWorkoutRoutineBean;
import beans.WorkoutDayBean;
import beans.WorkoutRoutineBean;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import model.WorkoutRoutine;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class SatisfyWorkoutRequestsController implements Initializable {

    //TODO da risistemare; occhio che da un punto di vista di svolgimento di codice questa classe deve estendere la classe

    //Tutti sti valori bean diventeranno poi model

    private final WorkoutRoutineBean workoutRoutine;
    private final String trainer;
    private final ArrayList<String> exerciseCatalogue;

    public SatisfyWorkoutRequestsController() {
        workoutRoutine = new WorkoutRoutineBean();
        trainer = "Personal Trainer Loggato";
        exerciseCatalogue  = new ArrayList<>() ;
    }

    public SatisfyWorkoutRequestsController(String trainer, ArrayList<String> exerciseCatalogue) {
        workoutRoutine = new WorkoutRoutineBean();
        this.trainer = trainer;
        this.exerciseCatalogue = exerciseCatalogue;
    }


    public void addExerciseToWorkoutDay(ExerciseForWorkoutRoutineBean exercise, String nameDay, ListView<ExerciseForWorkoutRoutineBean> RoutineExerciselist)  {
        //verifica se nella scheda c'è già un giorno con esercizi
        //in attesa delle bean passo i valori attraverso la GUIController

        RoutineExerciselist.getItems().add(exercise);
/*        this.workoutRoutine.*/


        /*
        WorkoutDay workoutDay = new WorkoutDay(nameDay, numDay);
        workoutDay = getWorkoutDay(nameDay);*/

        /*
        workoutDay.addExercise(new Exercise(
                bean.getExerciseBean().getId(),
                bean.getExerciseBean().getName(),
                bean.getExerciseBean().getInfo(),
                trainer
        ));
        */
    }


    public void submitRoutine(ListView<ExerciseForWorkoutRoutineBean> RoutineExerciselist) {
        // TODO sistema il submit
        for (ExerciseForWorkoutRoutineBean exercise : RoutineExerciselist.getItems()) {
            String exerciseDay = exercise.getDay();

            // Check if there is a WorkoutDayBean for the current day in the list
            Optional<WorkoutDayBean> workoutDayOptional = workoutRoutine.getWorkoutDayList().stream()
                    .filter(workoutDay -> workoutDay.getName().equals(exerciseDay))
                    .findFirst();

            // If a WorkoutDayBean for the current day is not found, create one
            WorkoutDayBean workoutDay = workoutDayOptional.orElseGet(() -> {
                WorkoutDayBean newWorkoutDay = new WorkoutDayBean(exerciseDay);
                workoutRoutine.addWorkoutDayBean(newWorkoutDay);
                return newWorkoutDay;
            });

            // Add the current exercise to the list of exercises for the current day
            workoutDay.addExerciseBean(new ExerciseBean(exercise.getExercise().getName()));
        }

        // Print details outside the loop
        for (ExerciseForWorkoutRoutineBean exercisePrint : RoutineExerciselist.getItems()) {
            ExerciseBean exerciseName = exercisePrint.getExercise();
            String dayP = exercisePrint.getDay();
            int repetitionsP = exercisePrint.getRepetitions();
            int setsP = exercisePrint.getSets();
            String restP = exercisePrint.getRest();
            System.out.println("Exercise: " + exerciseName.getName() +
                    ", Day: " + dayP +
                    ", Repetitions: " + repetitionsP +
                    ", Sets: " + setsP +
                    ", Rest: " + restP);
        }
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
