package engineering;

import database.dao.GymDAO;
import model.Exercise;
import model.ExerciseStatus;
import model.record.Credentials;

import java.util.List;

public class ExerciseInventory {

    private final List<Exercise> exerciseList;

    public ExerciseInventory(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void addExercise(Exercise exercise){
        this.exerciseList.add(exercise);
    }
    public static ExerciseInventory loadExcercise(String gymName){
        GymDAO gymdao=new GymDAO();
        return new ExerciseInventory(gymdao.loadDBExercises(gymName));
    }

}
