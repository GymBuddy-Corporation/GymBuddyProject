package controllers;

import beans.CredentialsBean;
import beans.UserBean;
import database.dao_classes.AthleteDAO;
import database.dao_classes.TrainerDAO;
import database.dao_classes.UserDAO;
import exceptions.dataException.DataFieldException;
import model.User;
import engineering.LoggedUserSingleton;

import java.util.List;


public class LoginController {

    //poi con un DB se toglie ovviamente
    private List<User> userList;

    public User getLoggedUser() /*throws SQLException, DBUnreachableException*/{
        /*User user = new AthleteDAO().loadAthlete(LoggedUserSingleton.getAthlete());
        if(user == null){
            user = new TrainerDAO().loadTrainer(LoggedUserSingleton.getTrainer());
        }
        *//*if(user == null){
            user = new GymDAO().loadTrainer(LoggedUserSingleton.getGymUsername());
        }*//*
        if(user == null){
            System.out.println("is not in DB.");
            *//*throw new IsNeitherATrainerNorAnAthleteException();*//*
        }*/
        return null/*user*/;
    }

    public UserBean login(CredentialsBean credentials) throws DataFieldException /*throws SQLException, DBUnreachableException, UserNotFoundException*/{
        User user = new UserDAO().loadUser(credentials.getEmail(), credentials.getPassword());
        return LoggedUserSingleton.getUserBean(user);
    }

    public void createUsers(){
        //userList;
    //TODO ricopia le istanze di atleti che hai creato nel progetto
    }
}
