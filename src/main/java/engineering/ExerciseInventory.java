package engineering;

import model.Exercise;
import model.ExerciseStatus;

import java.util.List;

public class ExerciseInventory implements Observer{

    private final List<Exercise> exerciseList;

    public ExerciseInventory(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    /*public void removeExercise(Exercise exerciseToDelete) {
        for(int i = 0; i < exerciseList.size(); i++) {
            if(exerciseList.get(i).getName().equals(exerciseToDelete.getName())) {
                exerciseList.remove(i);
                break;
            }
        }
        super.notifyObservers(exerciseToDelete);
    }*/

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    @Override
    public void update(Exercise exercise) {
        /*ExerciseStatusBean statusBean = getStatusBean(status);
        ObservableList<ExerciseBean> items = exerciseDBList.getItems();

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(exercise.getName())) {
                items.get(i).setStatusExercise(status);
                break;
            }
        }*/
    }
}
