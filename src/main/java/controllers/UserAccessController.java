package controllers;

import beans.CredentialsBean;
import beans.UserBean;
import database.dao.AthleteDAO;
import database.dao.UserDAO;
import engineering.LoggedAthleteSingleton;
import engineering.LoggedGymSingleton;
import engineering.LoggedTrainerSingleton;
import exceptions.*;

import exceptions.logger.CostumeLogger;
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

    public void logout() {
        UserDAO.eliminateSavedCredentials();
        LoggedUserSingleton.clearSingleton();
    }

    public UserBean login(CredentialsBean credentials,boolean saveCredentials) throws NoUserFoundException, AlreadyLoggedUserException, DBUnrreachableException {
        if (LoggedUserSingleton.getSingleton() != null) throw new AlreadyLoggedUserException();
        Credentials credentialsObj=new Credentials(credentials.getEmail(), credentials.getPassword());
        return loginCall(credentialsObj,saveCredentials);
    }

    public UserBean loginDeserialization() throws NoUserFoundException, AlreadyLoggedUserException, DBUnrreachableException {
        return loginCall(UserDAO.deserializeSavedCredentials(),true);
    }

    private  UserBean loginCall(Credentials credentials,boolean saveCredential) throws NoUserFoundException, AlreadyLoggedUserException, DBUnrreachableException {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.loadUser(credentials);
        if(saveCredential)UserDAO.serializeSavedCredential(credentials);
        if(user instanceof Gym gym){
            return LoggedGymSingleton.createGymSingleton(gym).getMyBean();
        } else if (user instanceof Athlete athlete) {
            try {
                new AthleteDAO().loadAthleteWallet(athlete);
            } catch (CostumException e) {
                CostumeLogger.getInstance().logError(e);
            }
            return LoggedAthleteSingleton.createAthleteSingleton(athlete).getMyBean();
        } else{
            return LoggedTrainerSingleton.createTrainerSingleton((Trainer) user).getMyBean();
        }
    }
}
