package controllers;

import beans.CredentialsBean;
import beans.UserBean;
import database.dao_classes.AthleteDAO;
import database.dao_classes.TrainerDAO;
import database.dao_classes.UserDAO;
import exceptions.AlreadyLoggedUserException;
import exceptions.CostumException;
import exceptions.UserCastException;
import exceptions.dataException.DataFieldException;
import model.User;
import engineering.LoggedUserSingleton;

import java.util.List;


public class UserAccessController {





    public UserBean login(CredentialsBean credentials) throws CostumException /*throws SQLException, DBUnreachableException, UserNotFoundException*/{
        //User in verita è un istanza di gym/athlete/pt
        User user = new UserDAO().loadUser(credentials.getEmail(), credentials.getPassword());
        return LoggedUserSingleton.createUserSingleton(user).geyMyBean();
    }


}
