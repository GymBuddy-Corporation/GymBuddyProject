package database.dao;

/*import database.DatabaseConnectionSingleton;
import database.queries.RequestQueries;
import exceptions.DBConnectionFailedException;
import exceptions.DBUnreachableException;*/

import database.SingletonConnection;
import database.query.Queries;
import exceptions.NoUserFoundException;
import model.*;
import model.record.Credentials;
import model.record.PersonalInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GymDAO {
    private static final String NAME = "gymName";
    private static final String ADDRESS = "gymAddress";
    private static final String USERNAME = "gymUsername";
    private static final String CITY = "gymCity";
    private static final String COUNTRY="gymCountry";
    private static final String IBAN = "gymIban";
    private static final String EMAIL = "gymEmail";
    private static final String PASSWORD = "Password";



    public List<Exercise> loadDBExercises(String gymName){
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

    public Gym getGymByName(String gymName) throws NoUserFoundException {
        loadAllGyms();
        for (Gym gym : loadedgyms) {
            if (gym.getGymName().equals(gymName)) {
                return gym;
            }
        }
        throw new NoUserFoundException();
    }
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

    public Gym loadGym(String email) throws SQLException, NoUserFoundException {

                PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(Queries.LOAD_USER_3_QUERY);
                ResultSet rs = Queries.loadUser(email, preparedStatement);
            if (rs.next()) {
                return new Gym(
                        rs.getString(USERNAME),
                        new Credentials(rs.getString(EMAIL),""),
                        rs.getString(IBAN),
                        rs.getString(CITY),
                        rs.getString(ADDRESS),
                        rs.getString(COUNTRY),
                        rs.getString(NAME)
                );

            } else {
                throw new NoUserFoundException();
            }

    }

}