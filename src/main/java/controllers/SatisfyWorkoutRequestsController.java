package controllers;

import beans.*;
import database.dao.RequestDAO;
import engineering.ExerciseInventory;
import engineering.LoggedUserSingleton;
import exceptions.UserCastException;
import exceptions.dataException.DataFieldException;
import javafx.application.Platform;
import javafx.scene.control.ListView;
import model.*;
import model.record.Credentials;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SatisfyWorkoutRequestsController {

    //TODO da risistemare; occhio che da un punto di vista di svolgimento di codice questa classe
    // deve scambiare i dati da bean a model

    public SatisfyWorkoutRequestsController() {}

    public List<ExerciseBean> getGymExerciseBean() {
        List<Exercise> exerciseList = LoggedUserSingleton.getSingleton().getExcerciseInventory().getExerciseList();
        return getExerciseBeanList(exerciseList, null);
    }

    public static ExerciseStatusBean convertToExerciseStatusBean(ExerciseStatus status) {
        return switch (status) {
            case ACTIVE -> ExerciseStatusBean.ACTIVE;
            case SUSPENDED -> ExerciseStatusBean.SUSPENDED;
            default -> null;  // Handle other cases or throw an exception
        };
    }

    public static ExerciseStatus convertFromExerciseStatusBean(ExerciseStatusBean statusBean) {
        return switch (statusBean) {
            case ACTIVE -> ExerciseStatus.ACTIVE;
            case SUSPENDED -> ExerciseStatus.SUSPENDED;
            default -> null;  // Handle other cases or throw an exception
        };
    }

    public boolean checkAlreadyAdded(ExerciseForWorkoutRoutineBean exerciseForWorkoutRoutineBean, List<ExerciseForWorkoutRoutineBean> routineExerciselist) {
        for (ExerciseForWorkoutRoutineBean exercise : routineExerciselist) {
            if (Objects.equals(exercise.getName(), exerciseForWorkoutRoutineBean.getName())) {
                System.out.println("Esercizio già inserito nella tua scheda\n");
                return true;
            }
        }
        return false;
    }

    public void setExerciseStatus(ExerciseBean exercise, ExerciseStatusBean status) throws UserCastException {
        //TODO
        ExerciseStatus statusToSet = convertFromExerciseStatusBean(status);

        // Update the status in the exerciseBean
        exercise.setStatusExercise(status);

        // Notify observers or perform any other necessary actions
        //findExerciseByName(exercise.getName(), statusToSet);
        for (Exercise ex : LoggedUserSingleton.getSingleton().getExcerciseInventory().getExerciseList()){
            if(ex.getName().equals(exercise.getName())){
                ex.setStatus(statusToSet);
                System.out.println(exercise.getName() + " ha lo stato " + exercise.getStatusExercise());
                /*ex.notifyObservers(ex.getName()); Gia lo notifico nella riga sopra*/
            }
        }
    }

    private void findExerciseByName(String exerciseName, ExerciseStatus status) {
        List<Exercise> exerciseList = LoggedUserSingleton.getSingleton().getExcerciseInventory().getExerciseList();
        for (Exercise exercise : exerciseList) {
            if (exercise.getName().equals(exerciseName)) {
                exercise.setStatus(status);

            }
        }
    }

    public void submitRoutine(RequestBean request, WorkoutRoutineBean workoutRoutineBean) {
        //TODO sistema poi il metodo con atleta in questione e invio scheda
        //Archivia la scheda vecchia
        //salva la nuova scheda
        //elimina la richiesta
        //notifica l'atletaWorkoutRoutine

        WorkoutRoutine workoutRoutineModel = new WorkoutRoutine();

        // Iterate through each WorkoutDayBean in the WorkoutRoutineBean
        for (WorkoutDayBean workoutDay : workoutRoutineBean.getWorkoutDayList()) {
            // Create a new WorkoutDay in the WorkoutRoutineModel
            WorkoutDay newWorkoutDay = new WorkoutDay(workoutDay.getName());

            // Iterate through each ExerciseForWorkoutRoutineBean in the WorkoutDayBean
            for (ExerciseForWorkoutRoutineBean exerciseForWorkoutRoutineBean : workoutDay.getExerciseList()) {
                // Convert ExerciseForWorkoutRoutineBean to ExerciseForWorkoutRoutine
                ExerciseForWorkoutRoutine exerciseForWorkoutRoutine = convertToExerciseForWorkoutRoutine(exerciseForWorkoutRoutineBean, workoutRoutineModel);

                // Add ExerciseForWorkoutRoutine to the new WorkoutDay
                newWorkoutDay.addExercise(exerciseForWorkoutRoutine);
            }

            // Add the new WorkoutDay to the WorkoutRoutineModel
            workoutRoutineModel.addWorkoutDay(newWorkoutDay);
        }

        printWorkoutRoutineDetails(workoutRoutineModel);
        //TODO sistema la requestBean, gestisci che deve succedere
    }

    // Helper method to convert ExerciseForWorkoutRoutineBean to ExerciseForWorkoutRoutine
    private ExerciseForWorkoutRoutine convertToExerciseForWorkoutRoutine(ExerciseForWorkoutRoutineBean exerciseForWorkoutRoutineBean, WorkoutRoutine workoutRoutineModel) {
        ExerciseForWorkoutRoutine exerciseForWorkoutRoutine = new ExerciseForWorkoutRoutine(exerciseForWorkoutRoutineBean.getName(), convertFromExerciseStatusBean(exerciseForWorkoutRoutineBean.getStatusExercise()), exerciseForWorkoutRoutineBean.getDay(), workoutRoutineModel.getName());
        exerciseForWorkoutRoutine.setRepetitions(exerciseForWorkoutRoutineBean.getRepetitions());
        exerciseForWorkoutRoutine.setSets(exerciseForWorkoutRoutineBean.getSets());
        exerciseForWorkoutRoutine.setRest(exerciseForWorkoutRoutineBean.getRest());
        // Set other properties as needed

        return exerciseForWorkoutRoutine;
    }

    // Helper method to print details of the WorkoutRoutineModel
    private void printWorkoutRoutineDetails(WorkoutRoutine workoutRoutineModel) {
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
        List<Exercise> exerciseList = LoggedUserSingleton.getSingleton().getExcerciseInventory().getExerciseList();
        List<Exercise> filteredExercises = new ArrayList<>();

        for (Exercise exercise : exerciseList) {
            if (exercise.getName().toLowerCase().contains(searchBean.getName().toLowerCase())) {
                filteredExercises.add(exercise);
            }
        }
        return getExerciseBeanList(null, filteredExercises);
    }

    @NotNull
    public List<ExerciseBean> getExerciseBeanList(List<Exercise> exerciseList, List<Exercise> exList) {
        List<ExerciseBean> exerciseBeanList = new ArrayList<>();
        if (exList!=null){
            for(Exercise exercise: exList){
                ExerciseStatusBean status = SatisfyWorkoutRequestsController.getExerciseStatusBeanFromExercise(exercise);
                exerciseBeanList.add(new ExerciseBean(exercise.getName(), status));
            }
            return exerciseBeanList;
        } else if(exerciseList!=null){
            for(Exercise exercise: exerciseList){
                ExerciseStatusBean status = SatisfyWorkoutRequestsController.getExerciseStatusBeanFromExercise(exercise);
                exerciseBeanList.add(new ExerciseBean(exercise.getName(), status));
            }
            return exerciseBeanList;
        } else {
            //throw Exception campi entrambi nulli o entrambi pieni
            System.out.println("campi entrambi nulli o entrambi pieni");
            return null;
        }
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

    public void removeExerciseFromDWorkoutRoutineBean (ExerciseForWorkoutRoutineBean exercise, WorkoutRoutineBean workoutRoutine) {
        // Iterate through the map and remove the exercise for the corresponding day
        workoutRoutine.getWorkoutDay(exercise.getDay()).removeExerciseBean(exercise);
    }

    public void removeExerciseToWorkoutDay(ExerciseForWorkoutRoutineBean selectedExercise, WorkoutRoutineBean workoutRoutine) {
        // Create a copy of the routineExerciselist items to avoid ConcurrentModificationException
        List<ExerciseForWorkoutRoutineBean> copyList = new ArrayList<>(workoutRoutine.getWorkoutDay(selectedExercise.getDay()).getExerciseList());

        for (ExerciseForWorkoutRoutineBean item : copyList) {
            if (selectedExercise.getName().equals(item.getName())) {
                //routineExerciselist.remove(item);
                removeExerciseFromDWorkoutRoutineBean(item, workoutRoutine);
                break;
            }
        }
    }

    public void addExerciseToWorkoutDay(ExerciseForWorkoutRoutineBean exercise, WorkoutRoutineBean workoutRoutine) {

        if (exercise.getSets() == 0 || exercise.getRepetitions() == 0) {
            System.out.println("Sets and repetitions must be greater than 0.");
            return;
        }

        WorkoutDayBean workoutDay = workoutRoutine.getWorkoutDay(exercise.getDay());

        // If the workout day doesn't exist, create it
        if (workoutDay == null) {
            workoutDay = new WorkoutDayBean(exercise.getDay());
            workoutRoutine.addWorkoutDayBean(workoutDay);
        }

        // Check if the exercise is already added to the workout day
        for (ExerciseForWorkoutRoutineBean existingExercise : workoutDay.getExerciseList()) {
            if (existingExercise.getName().equals(exercise.getName())) {
                System.out.println("Exercise '" + exercise.getName() + "' is already added to the workout day.");
                return; // Don't add the exercise if it's already in the workout day
            }
        }

        // Add the exercise to the workout day
        workoutDay.addExerciseBean(exercise);

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
           /* } catch (NoCardInsertedException e) {
                athleteBean = new AthleteBean(
                        usr.getUsername(),
                        new PersonalInfoBean(
                                usr.getName(),
                                usr.getSurname(),
                                usr.getDateOfBirth(),
                                usr.getFiscalCode(),
                                usr.getGender()
                        ),
                        CredentialsBean.ctorWithoutSyntaxCheck(
                                usr.getEmail(),
                                usr.getPassword()
                        ),
                        new CardInfoBean(
                                null,
                                (YearMonth) null
                        ));
            }*/
            requestBeanList.add(new RequestBean(
                    request.getId(),
                    request.getRequestDate(),
                    request.getInfo(),
                    athleteBean,
                    request.getTrainer().getFC()
            ));

        }
        return requestBeanList;
    }

    public void rejectRequest(RequestBean selectedRequest) {
        new RequestDAO().deleteRequest(selectedRequest.getRequestDate(),
                selectedRequest.getAthleteBean().getCredentials().getEmail());
        //notificationsController.sendRejectRequestNotification(selectedRequest.getAthleteBean().getFiscalCode());
        //create new model Request
        //get the dao request equal to the selected request
        //delete it
    }
}