package controllers;

import beans.CredentialsBean;
import beans.UserBean;
import database.SingletonConnection;
import database.dao.UserDAO;
import exceptions.AlreadyLoggedUserException;
import exceptions.NoUserFoundException;
import exceptions.dataException.DataFieldException;
import model.User;
import engineering.LoggedUserSingleton;

import java.sql.Connection;
import java.sql.SQLException;


public class UserAccessController {

    public UserBean login(CredentialsBean credentials) throws DataFieldException, NoUserFoundException, AlreadyLoggedUserException, SQLException {
        // User in verita è un istanza di gym/athlete/pt
        if (LoggedUserSingleton.getSingleton() != null) throw new AlreadyLoggedUserException();
        UserDAO userDAO = new UserDAO();
        User user = userDAO.loadUser(credentials.getEmail(), credentials.getPassword()); //null
        return LoggedUserSingleton.createUserSingleton(user).getMyBean();
    }
}
