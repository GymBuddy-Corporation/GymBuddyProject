package utils;

import beans.ExerciseBean;
import model.Exercise;
import model.Gym;
/* Poi sarà da mettere i model al posto di questi bean:
import models.Exercise;*/

import java.util.ArrayList;
import java.util.List;

    public class ExerciseCatalogue {
        private final List<Exercise> exerciseList;

        public ExerciseCatalogue() {
            this.exerciseList = new ArrayList<>();

            Gym gym1 = new Gym("Palestra1");
            Gym gym2 = new Gym("Palestra2");
            Gym gym3 = new Gym("Palestra3");
            Gym gym4 = new Gym("Palestra4");

            Exercise ex1 = new Exercise("Tricep Pushdown", gym1);
            Exercise ex2 = new Exercise("Shoulder Press", gym2);
            Exercise ex3 = new Exercise("Squat", gym3);
            Exercise ex4 = new Exercise("Dips", gym4);

            this.exerciseList.add(ex1);
            this.exerciseList.add(ex2);
            this.exerciseList.add(ex3);
            this.exerciseList.add(ex4);
        }

        public List<Exercise> getExerciseList() {
            return exerciseList;
        }

        public void addExercise(Exercise exercise) {
            exerciseList.add(exercise);
        }
    }
