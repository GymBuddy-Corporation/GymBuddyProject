package controllers;

import beans.*;
import boundaries.EmailSystemBoundary;
import database.dao.AthleteDAO;
import database.dao.ExerciseDAO;
import database.dao.RequestDAO;
import database.dao.WorkoutRoutineDAO;
import engineering.LoggedTrainerSingleton;
import exceptions.*;
import exceptions.dataexception.DataFieldException;
import exceptions.logger.CostumeLogger;
import model.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class SatisfyWorkoutRequestsController {

    public SatisfyWorkoutRequestsController() throws NoLoggedUserException{
        if(LoggedTrainerSingleton.getSingleton() == null){
            throw new NoLoggedUserException();
        }
    }

    public List<ExerciseBean> getLoggedTrainerGymExercises() {
        List<Exercise> exerciseList = LoggedTrainerSingleton.getSingleton().getExcerciseList();
        return getExerciseBeanList(exerciseList);
    }

    public void setExerciseStatus(ExerciseBean exercise, ExerciseStatus status)  {
        exercise.setStatusExercise(status);
        for (Exercise ex : LoggedTrainerSingleton.getSingleton().getExcerciseList()){
            if(ex.getName().equals(exercise.getName())){
                ex.setStatus(status);
                new ExerciseDAO().setExerciseStatus(ex, LoggedTrainerSingleton.getSingleton().getGym());
            }
        }
    }

    protected final WorkoutRoutine createWorkoutRoutine(WorkoutRoutineBean workoutRoutineBean){
        WorkoutRoutine workoutRoutineModel = new WorkoutRoutine(workoutRoutineBean.getName(), workoutRoutineBean.getComment());
        for (WorkoutDayBean workoutDay : workoutRoutineBean.getWorkoutDayList()) {
            WorkoutDay newWorkoutDay = new WorkoutDay(workoutDay.getName());
            for (ExerciseForWorkoutRoutineBean exerciseForWorkoutRoutineBean : workoutDay.getExerciseList()) {
                ExerciseForWorkoutRoutine exerciseForWorkoutRoutine = convertToExerciseForWorkoutRoutine(exerciseForWorkoutRoutineBean, workoutRoutineModel);
                newWorkoutDay.addExercise(exerciseForWorkoutRoutine);
            }
            workoutRoutineModel.addWorkoutDay(newWorkoutDay);
        }
        return workoutRoutineModel;
    }

    public void sendWorkoutRoutine(RequestBean requestBean, WorkoutRoutineBean workoutRoutineBean) throws DBUnrreachableException {
        WorkoutRoutine workoutRoutineModel = createWorkoutRoutine(workoutRoutineBean);
        Athlete receiver = new AthleteDAO().loadAthlete(requestBean.getAthleteBean().getCredentials().getEmail());
        if(receiver.getWorkoutRoutine() != null){
            new AthleteDAO().removeWorkoutRoutine(receiver.getFC());
        }
        new WorkoutRoutineDAO().saveWorkoutRoutine(
                workoutRoutineModel,
                requestBean.getAthleteBean().getPersonalInfo().getFc());
        new RequestDAO().deleteRequest(requestBean.getAthleteBean().getPersonalInfo().getFc(),
                requestBean.getTrainerFc());
    }

    private ExerciseForWorkoutRoutine convertToExerciseForWorkoutRoutine(ExerciseForWorkoutRoutineBean exerciseForWorkoutRoutineBean, WorkoutRoutine workoutRoutineModel) {
        ExerciseForWorkoutRoutine exerciseForWorkoutRoutine = new ExerciseForWorkoutRoutine(exerciseForWorkoutRoutineBean.getName(), exerciseForWorkoutRoutineBean.getStatusExercise(), exerciseForWorkoutRoutineBean.getDay(), workoutRoutineModel.getName());
        exerciseForWorkoutRoutine.setRepetitions(exerciseForWorkoutRoutineBean.getRepetitions());
        exerciseForWorkoutRoutine.setSets(exerciseForWorkoutRoutineBean.getSets());
        exerciseForWorkoutRoutine.setRest(exerciseForWorkoutRoutineBean.getRest());
        return exerciseForWorkoutRoutine;
    }

    public List<ExerciseBean> searchExercise(SearchBean searchBean) {
        List<Exercise> exerciseList = LoggedTrainerSingleton.getSingleton().getExcerciseList();
        List<Exercise> filteredExercises = new ArrayList<>();
        for (Exercise exercise : exerciseList) {
            if (exercise.getName().toLowerCase().contains(searchBean.getName().toLowerCase())) {
                filteredExercises.add(exercise);
            }
        }
        return getExerciseBeanList(filteredExercises);
    }

    public List<ExerciseBean> getExerciseBeanList(List<Exercise> exerciseList) {
        List<ExerciseBean> exerciseBeanList = new ArrayList<>();
        for(Exercise exercise: exerciseList){
            ExerciseStatus status = exercise.getStatus();
            exerciseBeanList.add(new ExerciseBean(exercise.getName(), status));
        }
        return exerciseBeanList;
    }

    public void sendEmailWithObject(EmailBean emailBean) throws URISyntaxException, IOException {
        sendEmail(emailBean);
    }

    public void sendEmailWithoutObject(EmailBean emailBean) throws EmailFormException, URISyntaxException, IOException {
        String creationObject = "NEW WORKOUT ROUTINE";
        emailBean.setObject(creationObject);
        sendEmail(emailBean);
    }
    private void sendEmail(EmailBean emailBean) throws URISyntaxException, IOException {
        try{
            new EmailSystemBoundary().sendEmail(emailBean);
        } catch (BrowsingNotSupportedException e){
            CostumeLogger.getInstance().logError(e);
        }
    }

    public List<RequestBean> getTrainerRequests() throws DBUnrreachableException, DataFieldException {
        List<Request> requestList = new ArrayList<>(new RequestDAO().loadTrainerRequests(LoggedTrainerSingleton.getSingleton().getUser()));
        List<RequestBean> requestBeanList = new ArrayList<>();
        for(Request request: requestList) {
            Athlete usr = request.getAthlete();
            AthleteBean athleteBean;
            athleteBean = new AthleteBean(
                    usr.getUsername(),
                    new PersonalInfoBean(
                            usr.getName(),
                            usr.getSurname(),
                            usr.getDateOfBirth(),
                            usr.getFC(),
                            usr.getGender()
                    ),
                    CredentialsBean.ctorWithoutSyntaxCheck(
                            usr.getEmail(),
                            usr.getPassword()),
                    usr.getTrainerFc());
            requestBeanList.add(new RequestBean(
                    request.getInfo(),
                    athleteBean,
                    request.getTrainer().getFC()
            ));

        }
        return requestBeanList;
    }

    public void rejectRequest(RequestBean selectedRequest) {
        new RequestDAO().deleteRequest(selectedRequest.getAthleteBean().getPersonalInfo().getFc(),
                selectedRequest.getTrainerFc());
    }
}