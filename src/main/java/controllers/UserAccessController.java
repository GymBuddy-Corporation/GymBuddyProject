package controllers;

import beans.CredentialsBean;
import beans.UserBean;
import database.SingletonConnection;
import database.dao.UserDAO;
import engineering.LoggedAthleteSingleton;
import engineering.LoggedGymSingleton;
import engineering.LoggedTrainerSingleton;
import exceptions.AlreadyLoggedUserException;
import exceptions.NoLoggedUserException;

import exceptions.NoUserFoundException;
import exceptions.dataException.DataFieldException;
import model.Athlete;
import model.Gym;
import model.Trainer;
import model.User;
import engineering.LoggedUserSingleton;
import model.record.Credentials;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;


public class UserAccessController {
    public UserBean getUser() throws DataFieldException, NoUserFoundException {
        return LoggedUserSingleton.getSingleton().getMyBean();
    }

    public void logout() throws NoLoggedUserException {
        File myObj = new File("credentials.ser");
        myObj.delete();
        LoggedUserSingleton.clearSingleton();
    }

    public UserBean login(CredentialsBean credentials) throws DataFieldException, NoUserFoundException, AlreadyLoggedUserException, SQLException {
        // User in verita è un istanza di gym/athlete/pt
        if (LoggedUserSingleton.getSingleton() != null) throw new AlreadyLoggedUserException();
        UserDAO userDAO = new UserDAO();
        Credentials credentialsObj=new Credentials(credentials.getEmail(), credentials.getPassword());
        return loginCall(credentialsObj);
    }

    public UserBean loginDerserialization() throws NoUserFoundException, SQLException, AlreadyLoggedUserException {
            Credentials credentials;
        try {
            FileInputStream fileIn = new FileInputStream("credentials.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            credentials = (Credentials) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
           // i.printStackTrace();
            throw new NoUserFoundException();
        } catch (ClassNotFoundException c) {
            throw new NoUserFoundException();
        }
        return loginCall(credentials);
    }

    private  UserBean loginCall(Credentials credentials) throws SQLException, NoUserFoundException, AlreadyLoggedUserException {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.loadUser(credentials);
        try {
            File myObj = new File("credentials.ser");
            myObj.delete();
            FileOutputStream fileOut = new FileOutputStream("credentials.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(credentials);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /tmp/employee.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
        if(user instanceof Gym gym){
            return LoggedGymSingleton.createGymSingleton(gym).getMyBean();
        } else if (user instanceof Athlete athlete) {
            return LoggedAthleteSingleton.createAthleteSingleton(athlete).getMyBean();
        } else{
            return LoggedTrainerSingleton.createTrainerSingleton((Trainer) user).getMyBean();
        }
    }
}
