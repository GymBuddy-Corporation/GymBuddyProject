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


public class UserAccessController {


    public UserBean getUser(){
        return LoggedUserSingleton.getSingleton().getMyBean();
    }

    public void logout() throws NoLoggedUserException {
        UserDAO.eliminateSavedCredentials();
        LoggedUserSingleton.clearSingleton();
    }

    public UserBean login(CredentialsBean credentials,boolean saveCredentials) throws DataFieldException, NoUserFoundException, AlreadyLoggedUserException {
        if (LoggedUserSingleton.getSingleton() != null) throw new AlreadyLoggedUserException();
        Credentials credentialsObj=new Credentials(credentials.getEmail(), credentials.getPassword());
        return loginCall(credentialsObj,saveCredentials);
    }

    public UserBean loginDeserialization() throws NoUserFoundException, AlreadyLoggedUserException {
        return loginCall(UserDAO.deserializeSavedCredentials(),true);
    }

    private  UserBean loginCall(Credentials credentials,boolean saveCredential) throws  NoUserFoundException, AlreadyLoggedUserException {
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
