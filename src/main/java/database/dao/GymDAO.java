package database.dao;

/*import database.DatabaseConnectionSingleton;
import database.queries.RequestQueries;
import exceptions.DBConnectionFailedException;
import exceptions.DBUnreachableException;*/

import model.*;
import model.record.Credentials;

import java.util.ArrayList;
import java.util.List;

public class GymDAO {
    private static final String NAME = "Name";
    private static final String ADDRESS = "Address";
    private static final String USERNAME = "Username";
    private static final String CITY = "City";
    private static final String IBAN = "Iban";
    private static final String EMAIL = "Email";
    private static final String PASSWORD = "Password";



    public List<Exercise> loadDBExercises(Credentials credentail){
        List<Exercise> exList = new ArrayList<>();

        //TODO ATTENZIONE RICORDA LA VIEW NON COMUNICA CON I MODEL
        // SISTEMA BENE

        Exercise ex1 = new Exercise("Tricep Pushdown");
        Exercise ex2 = new Exercise("Shoulder Press");
        Exercise ex3 = new Exercise("Squat");
        Exercise ex4 = new Exercise("Dips");

        ex3.setStatus(ExerciseStatus.SUSPENDED);
        ex4.setStatus(ExerciseStatus.SUSPENDED);

        exList.add(ex1);
        exList.add(ex2);
        exList.add(ex3);
        exList.add(ex4);
        return exList;
    }
    List<Gym> loadedgyms;
    public List<Gym> loadAllGyms(){
        if(loadedgyms!=null)return loadedgyms;
        List<Gym> gyms = new ArrayList<>();

        Gym palestra1 = new Gym("palestra1", new Credentials("gym@gmail.com", "forzanapule1926"),
                "BBBBBBBBBBBBBBBBBBBBBB", "roma", "Piazza dei Consoli, 11", "Gym fantastic","italy");
        gyms.add(palestra1);

        Gym palestra2 = new Gym("palestra2", new Credentials("gym2@gmail.com", "password2"),
                "BBBBBBBBBBBBBBBBBBBBBB", "milan", "Address 2", "Gym 2","italy");
        gyms.add(palestra2);

        Gym palestra3 = new Gym("palestra3", new Credentials("gym3@gmail.com", "password3"),
                "BBBBBBBBBBBBBBBBBBBBBB", "florence", "Addresscam 3", "Gym 3","italy");
        gyms.add(palestra3);

        Gym palestra4 = new Gym("palestra4", new Credentials("gym4@gmail.com", "password4"),
                "BBBBBBBBBBBBBBBBBBBBBB", "naples", "Addresscam 4", "Gym 4","italy");
        gyms.add(palestra4);

        Gym palestra5 = new Gym("palestra5", new Credentials("gym5@gmail.com", "password5"),
                "BBBBBBBBBBBBBBBBBBBBBB", "turin", "Addresscam 5", "Gym 5","italy");
        gyms.add(palestra5);
        loadedgyms=gyms;
        return gyms;
    }

}