package database.dao;

import database.SingletonConnection;
import exceptions.DBUnrreachableException;
import exceptions.NoUserFoundException;

import exceptions.logger.CostumeLogger;
import model.*;
import model.record.Credentials;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.sql.*;




public class UserDAO {

    private static final String GYM_ENUM_TYPE = "gym" ;
    private static final String TRAINER_ENUM_TYPE = "trainer" ;
    private static final String ATHLETE_ENUM_TYPE = "athlete" ;
    private static final Integer GYM_TYPE = 0 ;
    private static final Integer TRAINER_TYPE = 1 ;
    private static final Integer ATHLETE_TYPE = 2 ;

    private static final String  fileForCredentials="credentials.ser";


    private @NotNull User getUser(String username) throws SQLException, NoUserFoundException, DBUnrreachableException {
        AthleteDAO aDao = new AthleteDAO();
        Athlete ret = aDao.loadAthlete(username);
        if (ret != null)return ret;
        TrainerDAO tDao = new TrainerDAO();
        Trainer ret1 = tDao.loadTrainerWithAgregations(username);
        if(ret1 != null)return ret1;
        GymDAO gDao=new GymDAO();
        //aggiungere l'eccezione alle altre e circondarle da un try catch
        return gDao.loadGym(username);
    }

    public User loadUser(Credentials obj) throws NoUserFoundException  {
        Connection connection = SingletonConnection.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM gymbuddy.user WHERE email = ? AND password = ?")) {
            preparedStatement.setString(1, obj.email());
            preparedStatement.setString(2, obj.password());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return getUser(resultSet.getString("email")); //null //???
                } else {
                    throw new NoUserFoundException();
                }
            }
        } catch (SQLException | DBUnrreachableException sqlException) {
            sqlException.printStackTrace();
            // Handle the SQL exception, throware una nuova eccezioen dedicata per sql
            return null;
        }




    }


    public static Credentials deserializeSavedCredentials() throws NoUserFoundException {
        Credentials credentials;
        try {
            FileInputStream fileIn = new FileInputStream(fileForCredentials);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            credentials = (Credentials) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i) {
            throw new NoUserFoundException();
        }
        return credentials;
    }

    public static void serializeSavedCredential(Credentials credentials){
        try {
            eliminateSavedCredentials();
            FileOutputStream fileOut = new FileOutputStream(fileForCredentials);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(credentials);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            CostumeLogger.getInstance().logError(e);
        }
    }

    public  static void eliminateSavedCredentials(){
        File myObj = new File(fileForCredentials);
        myObj.delete();
    }






}
