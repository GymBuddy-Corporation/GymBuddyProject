package controllers;

import beans.CredentialsBean;
import beans.UserBean;
import model.User;

import java.util.List;


public class LoginController {

    //poi con un DB se toglie ovviamente
    private List<User> userList;

   /* public User getLoggedUser() *//*throws SQLException, DBUnreachableException*//*{
        User user = new ;
        if(user == null){
            user = new ;
        }
        if(user == null){
            System.out.println("Utente non nel sistema.");
        }
        return user;
    }*/

/*    public UserBean login(CredentialsBean credentials) *//*throws SQLException, DBUnreachableException, UserNotFoundException*//*{
        createUsers();
        for(User user : userList){
            if (credentials.getEmail() == user.getEmail()){
                if(credentials.getPassword() == user.getPassword()){
                    return ;
                }
            }
        }

        User user = new UserDAO().loadUser(credentials.getEmail(), credentials.getPassword());
        return LoggedUserSingleton.getUserBean(user);
    }*/

    public void createUsers(){
        //userList;
    //TODO ricopia le istanze di atleti che hai creato nel progetto
    }
}
