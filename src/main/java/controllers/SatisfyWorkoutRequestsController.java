package controllers;

import beans.WorkoutDayBean;
import beans.WorkoutRoutineBean;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import model.WorkoutRoutine;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
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


    public void addExerciseToWorkoutDay(String exercise, String nameDay, ListView<String> RoutineExerciselist)  {
        //verifica se nella scheda c'è già un giorno con esercizi
        //in attesa delle bean passo i valori attraverso la GUIController

        RoutineExerciselist.getItems().addAll(exercise);
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

    private WorkoutDayBean getWorkoutDay(String day) {
        //ragiona solo con le bean
        for(WorkoutDayBean workoutDay: workoutRoutine.getWorkoutDayList()){
            if(Objects.equals(workoutDay.getName(), day)) {
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
