package controllers;

import beans.AthleteBean;
import beans.RequestBean;
import beans.WorkoutRoutineBean;
import database.dao.RequestDAO;
import database.dao.WorkoutRoutineDAO;
import model.WorkoutRoutine;

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
        WorkoutRoutine workoutRoutine = new WorkoutRoutineDAO().loadWorkoutRoutine(athleteBean.getPersonalInfo().getFc()) ;
        return convertWorkoutRoutineBean(workoutRoutine);
    }
    public WorkoutRoutineBean convertWorkoutRoutineBean(WorkoutRoutine workoutRoutine){
        return new WorkoutRoutineBean();
    }
}
