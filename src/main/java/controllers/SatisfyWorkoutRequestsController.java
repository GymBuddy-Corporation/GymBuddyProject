package controllers;

import beans.*;
import boundaries.EmailSystemBoundary;
import database.dao.AthleteDAO;
import database.dao.RequestDAO;
import database.dao.WorkoutDayDAO;
import database.dao.WorkoutRoutineDAO;
import engineering.LoggedUserSingleton;
import exceptions.UserCastException;
import exceptions.dataException.DataFieldException;
import model.*;
import org.jetbrains.annotations.NotNull;
import beans.RequestBean;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.*;

public class SatisfyWorkoutRequestsController {

    //TODO da risistemare; occhio che da un punto di 
    // vista di svolgimento di codice questa classe
    // deve scambiare i dati da bean a model e viceversa

    public SatisfyWorkoutRequestsController() {}

    public List<ExerciseBean> getLoggedTrainerGymExercises() {
        List<Exercise> exerciseList = LoggedUserSingleton.getSingleton().getExcerciseList();
        return getExerciseBeanList(exerciseList);
    }

    public static ExerciseStatus convertFromExerciseStatusBean(ExerciseStatusBean statusBean) {
        return switch (statusBean) {
            case ACTIVE -> ExerciseStatus.ACTIVE;
            case SUSPENDED -> ExerciseStatus.SUSPENDED;
            default -> null;
        };
    }

    public void setExerciseStatus(ExerciseBean exercise, ExerciseStatusBean status) throws UserCastException {
        ExerciseStatus statusToSet = convertFromExerciseStatusBean(status);

        exercise.setStatusExercise(status);

        for (Exercise ex : LoggedUserSingleton.getSingleton().getExcerciseList()){
            if(ex.getName().equals(exercise.getName())){
                ex.setStatus(statusToSet);
                System.out.println(exercise.getName() + " ha lo stato " + exercise.getStatusExercise());
            }
        }
    }

    public void sendWorkoutRoutine(RequestBean requestBean, WorkoutRoutineBean workoutRoutineBean){
        //TODO sistema poi il metodo con atleta in questione e invio scheda
        //salva la nuova scheda
        //elimina la richiesta
        //notifica l'atletaWorkoutRoutine

        //todo gestisci sta cazzo di inizializzazione della scheda
        WorkoutRoutine workoutRoutineModel = new WorkoutRoutine(workoutRoutineBean.getName(), workoutRoutineBean.getComment());

        for (WorkoutDayBean workoutDay : workoutRoutineBean.getWorkoutDayList()) {
            WorkoutDay newWorkoutDay = new WorkoutDay(workoutDay.getName());
            for (ExerciseForWorkoutRoutineBean exerciseForWorkoutRoutineBean : workoutDay.getExerciseList()) {
                ExerciseForWorkoutRoutine exerciseForWorkoutRoutine = convertToExerciseForWorkoutRoutine(exerciseForWorkoutRoutineBean, workoutRoutineModel);
                newWorkoutDay.addExercise(exerciseForWorkoutRoutine);
            }

            workoutRoutineModel.addWorkoutDay(newWorkoutDay);
        }
        System.out.println("comment: " + workoutRoutineBean);

        printWorkoutRoutineDetails(workoutRoutineModel);
        //TODO sistema la requestBean, gestisci che deve succedere

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
        ExerciseForWorkoutRoutine exerciseForWorkoutRoutine = new ExerciseForWorkoutRoutine(exerciseForWorkoutRoutineBean.getName(), convertFromExerciseStatusBean(exerciseForWorkoutRoutineBean.getStatusExercise()), exerciseForWorkoutRoutineBean.getDay(), workoutRoutineModel.getName());
        exerciseForWorkoutRoutine.setRepetitions(exerciseForWorkoutRoutineBean.getRepetitions());
        exerciseForWorkoutRoutine.setSets(exerciseForWorkoutRoutineBean.getSets());
        exerciseForWorkoutRoutine.setRest(exerciseForWorkoutRoutineBean.getRest());

        return exerciseForWorkoutRoutine;
    }

    private void printWorkoutRoutineDetails(WorkoutRoutine workoutRoutineModel) {
        //TODO togli poi
        for (WorkoutDay workoutDay : workoutRoutineModel.getWorkoutDayList()) {
            String dayName = workoutDay.getDay();
            System.out.println("Giorno: " + dayName);

            for (ExerciseForWorkoutRoutine exercise : workoutDay.getExerciseList()) {
                String exerciseName = exercise.getName();
                int repetitionsP = exercise.getRepetitions();
                int setsP = exercise.getSets();
                String restP = exercise.getRest();

                System.out.println("Esercizio: " + exerciseName);
                System.out.println("Ripetizioni: " + repetitionsP);
                System.out.println("Sets:: " + setsP);
                System.out.println("Rest: " + restP);
                System.out.println("\n");
            }
        }
    }

    public List<ExerciseBean> searchExercise(SearchBean searchBean) throws UserCastException{
        List<Exercise> exerciseList = LoggedUserSingleton.getSingleton().getExcerciseList();
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
            ExerciseStatusBean status = SatisfyWorkoutRequestsController.getExerciseStatusBeanFromExercise(exercise);
            exerciseBeanList.add(new ExerciseBean(exercise.getName(), status));
        }
        return exerciseBeanList;
    }

    public static ExerciseStatusBean getExerciseStatusBeanFromExercise(Exercise exercise) {
        return switch (exercise.getStatus()) {
            case ACTIVE -> ExerciseStatusBean.ACTIVE;
            case SUSPENDED -> ExerciseStatusBean.SUSPENDED;
            default ->
                // Handle other cases or throw an exception
                    null;
        };
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
        List<Request> requestList = new ArrayList<>(new RequestDAO().loadTrainerRequests(LoggedUserSingleton.getSingleton().getSpecificUser(Trainer.class)));

        List<RequestBean> requestBeanList = new ArrayList<>();
        for(Request request: requestList) {
            Athlete usr = request.getAthlete();
            AthleteBean athleteBean;
            /*try {*/
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
                            usr.getPassword()
                    ));
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
        //notificationsController.sendRejectRequestNotification(selectedRequest.getAthleteBean().getFiscalCode());
        //create new model Request
        //get the dao request equal to the selected request
        //delete it
    }
}