package database.dao;

/*import database.DatabaseConnectionSingleton;
import database.queries.RequestQueries;
import exceptions.DBConnectionFailedException;
import exceptions.DBUnreachableException;*/

import model.*;

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



    public List<Exercise> loadDBExercises(Gym gym1){
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
}
