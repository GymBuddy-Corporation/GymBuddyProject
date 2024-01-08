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

import java.sql.Connection;
import java.sql.SQLException;


public class UserAccessController {
    public UserBean getUser() throws DataFieldException, NoUserFoundException {
        return LoggedUserSingleton.getSingleton().getMyBean();
    }

    public void logout() throws NoLoggedUserException {
        LoggedUserSingleton.clearSingleton();
    }

    public UserBean login(CredentialsBean credentials) throws DataFieldException, NoUserFoundException, AlreadyLoggedUserException, SQLException {
        // User in verita è un istanza di gym/athlete/pt
        if (LoggedUserSingleton.getSingleton() != null) throw new AlreadyLoggedUserException();
        UserDAO userDAO = new UserDAO();
        User user = userDAO.loadUser(credentials.getEmail(), credentials.getPassword());
        if(user instanceof Gym gym){
            return LoggedGymSingleton.createGymSingleton(gym).getMyBean();
        } else if (user instanceof Athlete athlete) {
            return LoggedAthleteSingleton.createAthleteSingleton(athlete).getMyBean();
        } else{
            return LoggedTrainerSingleton.createTrainerSingleton((Trainer) user).getMyBean();
        }
    }
}
