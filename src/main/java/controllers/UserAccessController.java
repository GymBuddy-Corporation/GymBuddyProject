package controllers;

import beans.CredentialsBean;
import beans.UserBean;
import database.dao_classes.AthleteDAO;
import database.dao_classes.TrainerDAO;
import database.dao_classes.UserDAO;
import exceptions.AlreadyLoggedUserException;
import exceptions.CostumException;
import exceptions.NoUserFoundException;
import exceptions.UserCastException;
import exceptions.dataException.DataFieldException;
import model.User;
import engineering.LoggedUserSingleton;

import java.util.List;


public class UserAccessController {





    public UserBean login(CredentialsBean credentials) throws DataFieldException, NoUserFoundException, AlreadyLoggedUserException, UserCastException /*throws SQLException, DBUnreachableException, UserNotFoundException*/{
        //User in verita è un istanza di gym/athlete/pt
        if(LoggedUserSingleton.getSingleton()!=null)throw new AlreadyLoggedUserException();
        User user = new UserDAO().loadUser(credentials.getEmail(), credentials.getPassword());
        return LoggedUserSingleton.createUserSingleton(user).geyMyBean();
    }


}
