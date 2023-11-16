package utils;

import beans.ExerciseBean;
/* Poi sarà da mettere i model al posto di questi bean:
import models.Exercise;*/

import java.util.ArrayList;
import java.util.List;

    public class ExerciseCatalogue {
        private final List<ExerciseBean> exerciseList;

        public ExerciseCatalogue() {
            this.exerciseList = new ArrayList<>();

            ExerciseBean ex1 = new ExerciseBean("Tricep Pushdown");
            ExerciseBean ex2 = new ExerciseBean("Shoulder Press");
            ExerciseBean ex3 = new ExerciseBean("Squat");
            ExerciseBean ex4 = new ExerciseBean("Dips");

            this.exerciseList.add(ex1);
            this.exerciseList.add(ex2);
            this.exerciseList.add(ex3);
            this.exerciseList.add(ex4);
        }

        public List<ExerciseBean> getExerciseList() {
            return exerciseList;
        }

        public void addExercise(ExerciseBean exercise) {
            exerciseList.add(exercise);
        }
    }
