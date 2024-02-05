package database.dao;



import database.SingletonConnection;
import database.query.Queries;
import exceptions.DBUnrreachableException;
import exceptions.NoUserFoundException;
import exceptions.logger.CostumeLogger;
import model.*;
import model.record.Credentials;
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



    public List<Exercise> loadDBExercises(String gymName) throws DBUnrreachableException {
        try(PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(
                Queries.LOAD_GYM_EXERCISES); ResultSet rs = Queries.loadTrainerExercises(gymName, preparedStatement)){
            return getExercises(rs);
        } catch (SQLException e) {
            CostumeLogger.getInstance().logError(e);
            throw new DBUnrreachableException();
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



    public List<Gym> loadAllGyms() throws SQLException {
        List<Gym> gyms = new ArrayList<>();
        PreparedStatement preparedStatement=SingletonConnection.getInstance().getConnection().prepareStatement(Queries.LOAD_ALL_GYMS);
        ResultSet resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            gyms.add( new Gym(
                    resultSet.getString(USERNAME),
                    new Credentials(resultSet.getString(EMAIL),""),
                    resultSet.getString(IBAN),
                    resultSet.getString(CITY),
                    resultSet.getString(ADDRESS),
                    resultSet.getString(COUNTRY),
                    resultSet.getString(NAME)
            ));
        }
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

    public Gym loadGymByTrainerFc(String fc) throws DBUnrreachableException {
    try {
        PreparedStatement statement = SingletonConnection.getInstance().getConnection().prepareStatement(Queries.LOAD_GYM_BY_TRAINER_FC);
        ResultSet rs = Queries.loadAndExecuteOneString(fc, statement);
        rs.next();
        String email = rs.getString(EMAIL);
        Gym gym;
        gym = loadGym(email);
        return gym;
    }catch (SQLException e){
        CostumeLogger.getInstance().logError(e);
        throw new DBUnrreachableException();
    } catch (NoUserFoundException e) {
        CostumeLogger.getInstance().logError(e);
        CostumeLogger.getInstance().logError(e);
    }
    return null ;
    }

    public Gym getGymByName(String name) throws NoUserFoundException, DBUnrreachableException {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(Queries.LOAD_USER_GYM_BY_NAME_QUERRT);
            return getGym(name, preparedStatement);
        } catch (SQLException e) {
            CostumeLogger.getInstance().logError(e);
            throw new DBUnrreachableException();
        }
    }

}