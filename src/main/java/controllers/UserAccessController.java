package controllers;

import beans.CredentialsBean;
import beans.UserBean;
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
import java.sql.SQLException;


public class UserAccessController {


    public UserBean getUser(){
        return LoggedUserSingleton.getSingleton().getMyBean();
    }

    public void logout() throws NoLoggedUserException {
        UserDAO.eliminateSavedCredentials();
        LoggedUserSingleton.clearSingleton();
    }

    public UserBean login(CredentialsBean credentials,boolean saveCredentials) throws DataFieldException, NoUserFoundException, AlreadyLoggedUserException, SQLException {
        if (LoggedUserSingleton.getSingleton() != null) throw new AlreadyLoggedUserException();
        UserDAO userDAO = new UserDAO();
        Credentials credentialsObj=new Credentials(credentials.getEmail(), credentials.getPassword());
        return loginCall(credentialsObj,saveCredentials);
    }

    public UserBean loginDeserialization() throws NoUserFoundException, SQLException, AlreadyLoggedUserException {
        return loginCall(UserDAO.deserializeSavedCredentials(),true);
    }

    private  UserBean loginCall(Credentials credentials,boolean saveCredential) throws SQLException, NoUserFoundException, AlreadyLoggedUserException {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.loadUser(credentials);
        if(saveCredential)UserDAO.serializeSavedCredential(credentials);
        if(user instanceof Gym gym){
            return LoggedGymSingleton.createGymSingleton(gym).getMyBean();
        } else if (user instanceof Athlete athlete) {
            return LoggedAthleteSingleton.createAthleteSingleton(athlete).getMyBean();
        } else{
            return LoggedTrainerSingleton.createTrainerSingleton((Trainer) user).getMyBean();
        }
    }
}
