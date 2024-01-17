package controllers;

import beans.*;
import boundaries.EmailSystemBoundary;
import database.dao.AthleteDAO;
import database.dao.ExerciseDAO;
import database.dao.RequestDAO;
import database.dao.WorkoutRoutineDAO;
import engineering.LoggedTrainerSingleton;
import exceptions.UserCastException;
import exceptions.dataException.DataFieldException;
import model.*;
import org.jetbrains.annotations.NotNull;
import beans.RequestBean;
import viewtwo.beans.DayBeanB;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class SatisfyWorkoutRequestsController {

    public SatisfyWorkoutRequestsController() {}

    public List<ExerciseBean> getLoggedTrainerGymExercises() {
        List<Exercise> exerciseList = LoggedTrainerSingleton.getSingleton().getExcerciseList();
        return getExerciseBeanList(exerciseList);
    }

    public void setExerciseStatus(ExerciseBean exercise, ExerciseStatus status) throws UserCastException {

        exercise.setStatusExercise(status);

        for (Exercise ex : LoggedTrainerSingleton.getSingleton().getExcerciseList()){
            if(ex.getName().equals(exercise.getName())){
                ex.setStatus(status);
                //todo set status on DB new ExerciseDAO();
                new ExerciseDAO().setExerciseStatus(ex, LoggedTrainerSingleton.getSingleton().getGym());
                System.out.println(exercise.getName() + " ha lo stato " + exercise.getStatusExercise());
            }
        }
    }

    public void sendWorkoutRoutine(RequestBean requestBean, WorkoutRoutineBean workoutRoutineBean){
        //TODO sistema poi il metodo con atleta in questione e invio scheda
        //salva la nuova scheda
        //elimina la richiesta
        //notifica l'atleta
        WorkoutRoutine workoutRoutineModel = new WorkoutRoutine(workoutRoutineBean.getName(), workoutRoutineBean.getComment());

        for (WorkoutDayBean workoutDay : workoutRoutineBean.getWorkoutDayList()) {
            WorkoutDay newWorkoutDay = new WorkoutDay(workoutDay.getName());
            for (ExerciseForWorkoutRoutineBean exerciseForWorkoutRoutineBean : workoutDay.getExerciseList()) {
                ExerciseForWorkoutRoutine exerciseForWorkoutRoutine = convertToExerciseForWorkoutRoutine(exerciseForWorkoutRoutineBean, workoutRoutineModel);
                newWorkoutDay.addExercise(exerciseForWorkoutRoutine);
            }

            workoutRoutineModel.addWorkoutDay(newWorkoutDay);
        }

        Athlete receiver = new AthleteDAO().loadAthlete(requestBean.getAthleteBean().getCredentials().getEmail());
        if(receiver.getWorkoutRoutine() != null){
            new AthleteDAO().removeWorkoutPlan(receiver.getFC());
        }
        new WorkoutRoutineDAO().saveWorkoutRoutine(
                workoutRoutineModel,
                requestBean.getAthleteBean().getPersonalInfo().getFc()

        );
        new RequestDAO().deleteRequest(requestBean.getAthleteBean().getPersonalInfo().getFc(), requestBean.getTrainerFc());
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

    @NotNull
    public List<ExerciseBean> getExerciseBeanList(List<Exercise> exerciseList) {
        List<ExerciseBean> exerciseBeanList = new ArrayList<>();
        for(Exercise exercise: exerciseList){
            ExerciseStatus status = exercise.getStatus();
            exerciseBeanList.add(new ExerciseBean(exercise.getName(), status));
        }
        return exerciseBeanList;
    }

    public void sendClarificationEmail(UserBean sender, UserBean receiver, String object, String content) throws URISyntaxException, IOException{
        new EmailSystemBoundary().sendEmail(new EmailBean(
                sender,
                receiver,
                object,
                content
        ));
        System.out.println("Sender: " + sender + " Receiver: " + receiver + " object: " + object + " content: " + content);
    }

    public List<RequestBean> getTrainerRequests() throws DataFieldException /*throws SQLException, DBUnreachableException*/ {
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
                    usr.getTrainer().getFC());
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