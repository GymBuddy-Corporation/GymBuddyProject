package engineering;

import beans.*;
import model.Athlete;
import model.Gym;
import model.Trainer;
import model.User;

public class LoggedUserSingleton {

    protected static LoggedUserSingleton me=null;
    protected final User user;
    protected   UserTypes userType;

    protected LoggedUserSingleton(User temp) {
        if(temp instanceof Gym)userType=UserTypes.GYM;
        else if (temp instanceof Athlete)userType=UserTypes.ATHLETE;
        else if (temp instanceof Trainer)userType=UserTypes.PT;
        user=temp;
    }

    public static LoggedUserSingleton getSingleton()
     {
        return me;
    }

    public static void clearSingleton(){
        LoggedUserSingleton.me=null;
    }

    public User getUser(){
        return user;
    }

        public UserBean getMyBean()  {
        return getUserBean(user);
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
                    athlete.getTrainerFc());
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
        return null;
     }

    public UserTypes getUserType() {
        return userType;
    }

}
