package engineering;

import beans.*;

import exceptions.AlreadyLoggedUserException;
import exceptions.NoLoggedUserException;
import exceptions.dataException.DataFieldException;
import model.*;
import java.util.List;

public class LoggedUserSingleton {

    protected final User user;
    protected   UserTypes userType;
    protected static LoggedUserSingleton me=null;

    protected LoggedUserSingleton(User temp) {
        if(temp instanceof Gym)userType=UserTypes.gym;
        else if (temp instanceof Athlete)userType=UserTypes.athlete;
        else if (temp instanceof Trainer)userType=UserTypes.pt;
        user=temp;
    }



    public User getUser(){
        return user;
    }


    public static LoggedUserSingleton getSingleton()
     {
        return me;
    }

    public static void clearSingleton(){
        LoggedUserSingleton.me=null;
    }


    public static UserBean getUserBean(User usr)
     {
        if (usr instanceof Athlete athlete) {
            return new AthleteBean(
                    usr.getUsername(),
                    new PersonalInfoBean(
                            athlete.getName(),
                            athlete.getSurname(),
                            athlete.getDateOfBirth(),
                            athlete.getFC(),
                            athlete.getGender()),
                    CredentialsBean.ctorWithoutSyntaxCheck(
                            athlete.getEmail(),
                            athlete.getPassword()),
                    athlete.getTrainer().getFC());
        } else if (usr instanceof Trainer trainer) {
            return new TrainerBean(
                    usr.getUsername(),
                    new PersonalInfoBean(
                            trainer.getName(),
                            trainer.getSurname(),
                            trainer.getDateOfBirth(),
                            trainer.getFC(),
                            trainer.getGender()),
                    CredentialsBean.ctorWithoutSyntaxCheck(
                            trainer.getEmail(),
                            trainer.getPassword()));
        }else if (usr instanceof  Gym gym) {
            return  new GymBean(
                    gym.getUsername(),
                    CredentialsBean.ctorWithoutSyntaxCheck(
                            gym.getEmail(),
                            gym.getPassword()
                    ),
                    new GymInfoBean(
                            gym.getGymName(),
                            gym.getAddress(),
                            gym.getCity(),
                            gym.getGymName(),
                            gym.getCountry()
                    )
            );
        }
        //throw new NoUserFoundException();
        return null;
     }

        public UserBean getMyBean()  {
        return getUserBean(user);
        }

    public UserTypes getUserType() {
        return userType;
    }

}
