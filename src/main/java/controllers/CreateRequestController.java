package controllers;

import beans.*;
import database.dao.RequestDAO;
import database.dao.WorkoutRoutineDAO;
import model.ExerciseForWorkoutRoutine;
import model.WorkoutDay;
import model.WorkoutRoutine;

import java.util.ArrayList;
import java.util.List;

public class CreateRequestController {
    public void askForNewWorkoutRoutine(RequestBean requestBean){
        new RequestDAO().saveRequest(
                requestBean.getRequestDate(),
                requestBean.getInfo(),
                requestBean.getAthleteBean().getPersonalInfo().getFc(),
                requestBean.getTrainerFc()
        );
    }

    public WorkoutRoutineBean loadWorkoutRoutine(AthleteBean athleteBean){
        //todo verifica se funziona
        WorkoutRoutine workoutRoutine = new WorkoutRoutineDAO().loadWorkoutRoutine(athleteBean.getPersonalInfo().getFc()) ;
        return convertWorkoutRoutineBean(workoutRoutine);
    }
    private WorkoutRoutineBean convertWorkoutRoutineBean(WorkoutRoutine workoutRoutine){
        WorkoutRoutineBean workoutRoutineBean = new WorkoutRoutineBean();
        for(WorkoutDay workoutDay : workoutRoutine.getWorkoutDayList()){
            workoutRoutineBean.addWorkoutDayBean(new WorkoutDayBean(workoutDay.getDay(), getListExerciseBean(workoutDay)));
        }
        return workoutRoutineBean;
    }

    public List<ExerciseForWorkoutRoutineBean> getListExerciseBean(WorkoutDay workoutDay){
        List<ExerciseForWorkoutRoutineBean> listExercise = new ArrayList<>();
        for(ExerciseForWorkoutRoutine exercise : workoutDay.getExerciseList()){
            ExerciseForWorkoutRoutineBean exe = new ExerciseForWorkoutRoutineBean(
                    exercise.getName(), exercise.getStatus(), exercise.getDay(),
                    exercise.getRepetitions(), exercise.getSets(), exercise.getRest());
            listExercise.add(exe);
        }
        return listExercise;
    }
}
