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
import org.jetbrains.annotations.NotNull;

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



    public List<Exercise> loadDBExercises(String gymName) {
        try(PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(
                Queries.LOAD_GYM_EXERCISES); ResultSet rs = Queries.loadTrainerExercises(gymName, preparedStatement)){
            return getExercises(rs);
        } catch (SQLException e) {
            //TODO handle exception
            return null;
        }
    }

    private ArrayList<Exercise> getExercises(ResultSet rs) throws SQLException {
        ArrayList<Exercise> exerciseList = new ArrayList<>();
        while (rs.next()) {
            String name = rs.getString("nameEx");
            String statusString = rs.getString("status");
            // Assuming you have a method to convert a string to ExerciseStatus enum
            ExerciseStatus status = convertStringToExerciseStatus(statusString);
            exerciseList.add(new Exercise(name, status));
        }
        return exerciseList;
    }

    private ExerciseStatus convertStringToExerciseStatus(String statusString) {
        if ("ACTIVE".equals(statusString)) {
            return ExerciseStatus.ACTIVE;
        } else if ("SUSPENDED".equals(statusString)) {
            return ExerciseStatus.SUSPENDED;
        } else {
            throw new IllegalArgumentException("Invalid ExerciseStatus: " + statusString);
        }
    }



    public List<Gym> loadAllGyms(){
        List<Gym> gyms = new ArrayList<>();
        //todo gestisci tutto questo
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
        return gyms;
    }

    public Gym loadGym(String email) throws SQLException, NoUserFoundException {
                PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(Queries.LOAD_USER_GYM_BY_EMAIL_QUERY);
                return getGym(email, preparedStatement);

    }

    @NotNull
    private Gym getGym(String email, PreparedStatement preparedStatement) throws SQLException, NoUserFoundException {
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

    public Gym loadGymByTrainerFc(String fc) throws SQLException {
        PreparedStatement statement=SingletonConnection.getInstance().getConnection().prepareStatement(Queries.LOAD_GYM_BY_TRAINER_FC);
        ResultSet rs=Queries.loadAndExecuteOneString(fc,statement);
        rs.next();
        String email=rs.getString(EMAIL);
        Gym gym=null;
        try {
            gym = loadGym(email);
        } catch (NoUserFoundException ignore) {
        }
        return gym;
    }

    public Gym getGymByName(String name) throws SQLException, NoUserFoundException {
        PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(Queries.LOAD_USER_GYM_BY_NAME_QUERRT);
        return getGym(name, preparedStatement);
    }

}