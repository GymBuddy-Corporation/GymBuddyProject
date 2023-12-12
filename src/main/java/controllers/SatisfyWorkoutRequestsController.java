package controllers;

import beans.*;
import database.dao_classes.ExerciseDAO;
import database.dao_classes.RequestDAO;
import engineering.ExerciseInventory;
import javafx.scene.control.ListView;
import model.*;
import model.record.Credentials;
import model.record.PersonalInfo;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.*;

public class SatisfyWorkoutRequestsController {

    //TODO da risistemare; occhio che da un punto di vista di svolgimento di codice questa classe
    // deve scambiare i dati da bean a model

    private final WorkoutRoutine workoutRoutine;
    private final Trainer trainer;
    private final ExerciseInventory exerciseList;

    public List<ExerciseBean> getGymExerciseBean() {
        System.out.println(exerciseList);
        return getExerciseBeanList(exerciseList, null);
    }

    public SatisfyWorkoutRequestsController() {
        this.workoutRoutine = new WorkoutRoutine();
        Gym palestra1 = new Gym("palestra1", new Credentials("alecortix@gmail.com", "forzanapule1926"),
                "BBBBBBBBBBBBBBBBBBBBBB", "roma", "Piazza dei Consoli, 11") ;
        this.trainer = new Trainer("AleCortix",
                new PersonalInfo("Alessandro", "Cortese", LocalDate.now(), "CRTLSN99T24H501R", 'm'),
                new Credentials("alecortix@gmail.com", "forzanapule1926"), palestra1)
        /*(Trainer) new LoginController().getLoggedUser()*/;
        System.out.println(this.trainer.getName() + this.trainer.getEmail());
        //TODO organizza exercises
        this.exerciseList = new ExerciseInventory(new ArrayList<>(new ExerciseDAO().loadDBExercises()));
    }

    public static ExerciseBean convertFromExercise(Exercise exercise, String gym) {
        ExerciseStatusBean statusBean = convertToExerciseStatusBean(exercise.getStatus());
        return new ExerciseBean(exercise.getName(), statusBean, gym);
    }

    public static ExerciseStatusBean convertToExerciseStatusBean(ExerciseStatus status) {
        return switch (status) {
            case ACTIVE -> ExerciseStatusBean.ACTIVE;
            case SUSPENDED -> ExerciseStatusBean.SUSPENDED;
            default -> null;  // Handle other cases or throw an exception
        };
    }

    public WorkoutDayBean getWorkoutDayBean(DayBean dayBean) {
        for(WorkoutDay workoutDay: workoutRoutine.getWorkoutDayList()){
            if(Objects.equals(workoutDay.getDay(), dayBean.getDay())){
                WorkoutDayBean workoutDayBean = new WorkoutDayBean(workoutDay.getDay());
                for(ExerciseForWorkoutRoutine exercise: workoutDay.getExerciseList()){
                    workoutDayBean.addExerciseBean(new ExerciseForWorkoutRoutineBean(
                            exercise.getDay(),
                            convertFromExercise(exercise.getExercise(), trainer.getGym().getEmail()),
                            exercise.getRepetitions(),
                            exercise.getSets(),
                            exercise.getRest())
                    );
                }
                return workoutDayBean;
            }
        }
        return new WorkoutDayBean(dayBean.getDay());
    }

    public SatisfyWorkoutRequestsController(Trainer trainer, ExerciseInventory exerciseList) {
        this.workoutRoutine = new WorkoutRoutine();
        this.trainer = trainer;
        this.exerciseList = exerciseList;
        for (Exercise exercise : exerciseList.getExerciseList()) {
            System.out.println(exercise.getName());
        }
    }
    public static Exercise convertFromExerciseBean(ExerciseBean exerciseBean, Gym gym) {
        ExerciseStatus status = convertFromExerciseStatusBean(exerciseBean.getStatusExercise());
        return new Exercise(exerciseBean.getName(), gym, status);
    }

    public static ExerciseStatus convertFromExerciseStatusBean(ExerciseStatusBean statusBean) {
        return switch (statusBean) {
            case ACTIVE -> ExerciseStatus.ACTIVE;
            case SUSPENDED -> ExerciseStatus.SUSPENDED;
            default -> null;  // Handle other cases or throw an exception
        };
    }
    private WorkoutDay getWorkoutDay(String day) {
        for(WorkoutDay workoutDay: workoutRoutine.getWorkoutDayList()){
            if(Objects.equals(workoutDay.getDay(), day)) {
                return workoutDay;
            }
        }
        return null;
    }



    public void addExerciseToWorkoutDay(ExerciseForWorkoutRoutineBean exercise, List<ExerciseForWorkoutRoutineBean> RoutineExerciselist)  {
        WorkoutDay workoutDay = getWorkoutDay(exercise.getDay());
        if(workoutDay == null) {
            List<ExerciseForWorkoutRoutine> newList= new ArrayList<>();
            workoutDay = new WorkoutDayObserver(
                    exercise.getDay(),
                    newList,
                    workoutRoutine);
            workoutRoutine.addWorkoutDay(workoutDay);
        }
        workoutDay.addExercise(new ExerciseForWorkoutRoutine(
                exercise.getDay(),
                convertFromExerciseBean(exercise.getExercise(), trainer.getGym()),
                exercise.getRepetitions(),
                exercise.getSets(),
                exercise.getRest(),
                workoutRoutine.getName()
        ));
        RoutineExerciselist.add(exercise);
    }

    public void setExerciseStatus(ExerciseBean exercise, ExerciseStatusBean status){
        //TODO
        Exercise exerciseToEdit = new Exercise(
                exercise.getName(),
                trainer.getGym(),
                convertFromExerciseStatusBean(exercise.getStatusExercise()));
        new ExerciseDAO().changeExerciseStatus(exerciseToEdit, convertFromExerciseStatusBean(status));
        //insert here my request

        /*exerciseList.getExerciseList();
        exerciseCatalogue.removeExercise(exerciseToEdit);*/
        exercise.setStatusExercise(status);
    }

    public void submitRoutine(RequestBean request) {
        //TODO sistema poi il metodo con atleta in questione e invio scheda
        //Archivia la scheda vecchia
        //salva la nuova scheda
        //elimina la richiesta
        //notifica l'atleta
        for (WorkoutDay workoutDay : workoutRoutine.getWorkoutDayList()) {
            String dayName = workoutDay.getDay();

            // Iterate through each ExerciseForWorkoutRoutineBean in the WorkoutDayBean
            for (ExerciseForWorkoutRoutine exercisePrint : workoutDay.getExerciseList()) {
                Exercise exerciseName = exercisePrint.getExercise();
                int repetitionsP = exercisePrint.getRepetitions();
                int setsP = exercisePrint.getSets();
                String restP = exercisePrint.getRest();
                System.out.println("Giorno: " + dayName);
                System.out.println("Esercizio: " + exerciseName);
                System.out.println("Ripetizioni: " + repetitionsP);
                System.out.println("Sets:: " + setsP);
                System.out.println("Rest: " + restP);
                System.out.println("\n");
            }
        }
    }
    public List<ExerciseBean> searchExercise(SearchBean searchBean) {
        System.out.println(searchBean.getName());

        List<Exercise> filteredExercises = new ArrayList<>();
        for (Exercise exercise : exerciseList.getExerciseList()) {
            System.out.println(exercise.getName());
        }

        for (Exercise exercise : exerciseList.getExerciseList()) {
            if (exercise.getName().toLowerCase().contains(searchBean.getName().toLowerCase())) {
                filteredExercises.add(exercise);
            }
        }
        return getExerciseBeanList(null, filteredExercises);
    }

    @NotNull
    public List<ExerciseBean> getExerciseBeanList(ExerciseInventory exerciseList, List<Exercise> exList) {
        List<ExerciseBean> exerciseBeanList = new ArrayList<>();
        if (exList!=null){
            for(Exercise exercise: exList){
                ExerciseStatusBean ex = SatisfyWorkoutRequestsController.getExerciseStatusBeanFromExercise(exercise);
                exerciseBeanList.add(new ExerciseBean(exercise.getName(), ex, exercise.getGym().getEmail()));
            }
            return exerciseBeanList;
        } else if(exerciseList.getExerciseList()!=null){
            for(Exercise exercise: exerciseList.getExerciseList()){
                ExerciseStatusBean ex = SatisfyWorkoutRequestsController.getExerciseStatusBeanFromExercise(exercise);
                exerciseBeanList.add(new ExerciseBean(exercise.getName(), ex, exercise.getGym().getEmail()));
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

    public void removeExerciseFromDayExercisesMap(ExerciseForWorkoutRoutineBean exercise, Map<String, List<ExerciseForWorkoutRoutineBean>> dayExercisesMap) {
        // Iterate through the map and remove the exercise for the corresponding day
        dayExercisesMap.forEach((day, exercises) -> exercises.removeIf(e -> e.equals(exercise)));
    }

    public void removeExerciseToWorkoutDay(ExerciseBean selectedExercise, ListView<ExerciseForWorkoutRoutineBean> routineExerciselist, Map<String, List<ExerciseForWorkoutRoutineBean>> dayExercisesMap) {
        // Create a copy of the routineExerciselist items to avoid ConcurrentModificationException
        List<ExerciseForWorkoutRoutineBean> copyList = new ArrayList<>(routineExerciselist.getItems());

        for (ExerciseForWorkoutRoutineBean item : copyList) {
            if (selectedExercise.equals(item.getExercise())) {
                routineExerciselist.getItems().remove(item);

                // Remove the exercise from the dayExercisesMap
                removeExerciseFromDayExercisesMap(item, dayExercisesMap);

                break; // Exit the loop once the item is removed
            }
        }
    }

    public List<RequestBean> getTrainerRequests() /*throws SQLException, DBUnreachableException*/ {
        List<Request> requestList = new ArrayList<>(new RequestDAO().loadTrainerRequests(trainer));

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
