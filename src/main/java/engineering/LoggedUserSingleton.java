package engineering;

import beans.*;

import exceptions.AlreadyLoggedUserException;
import exceptions.UserCastException;
import exceptions.dataException.DataFieldException;
import model.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.LongFunction;

public class LoggedUserSingleton {



    private final User user;
    private  UserTypes userType;
    private  static LoggedUserSingleton me=null;

    private LoggedUserSingleton(User temp) {
        if(temp instanceof Gym)userType=UserTypes.gym;
        else if (temp instanceof Athlete)userType=UserTypes.athlete;
        else if (temp instanceof Trainer)userType=UserTypes.pt;
        user=temp;
    }



    public static LoggedUserSingleton getSingleton() {
        if(me==null)return null;
        return me;
    }

    public static LoggedUserSingleton createUserSingleton(User temp) throws AlreadyLoggedUserException {
        if(me==null){
            LoggedUserSingleton.me= new LoggedUserSingleton(temp);
            return LoggedUserSingleton.me;
        }else {
            throw new AlreadyLoggedUserException();
        }
    }
    public static void clearSingleton(){
        me=null;
    }


    public static UserBean getUserBean(User usr) throws DataFieldException, UserCastException {
        if (usr instanceof Athlete) {
            return new AthleteBean(
                    usr.getUsername(),
                    new PersonalInfoBean(
                            ((Person) usr).getName(),
                            ((Person) usr).getSurname(),
                            ((Person) usr).getDateOfBirth(),
                            ((Person) usr).getFC(),
                            ((Person) usr).getGender()),
                    CredentialsBean.ctorWithoutSyntaxCheck(
                            usr.getEmail(),
                            usr.getPassword()));
        } else if (usr instanceof Trainer) {
            return new TrainerBean(
                    usr.getUsername(),
                    new PersonalInfoBean(
                            ((Person) usr).getName(),
                            ((Person) usr).getSurname(),
                            ((Person) usr).getDateOfBirth(),
                            ((Person) usr).getFC(),
                            ((Person) usr).getGender()),
                    CredentialsBean.ctorWithoutSyntaxCheck(
                            usr.getEmail(),
                            usr.getPassword()));
        }else if (usr instanceof  Gym) {
            return  new GymBean(usr.getUsername(),CredentialsBean.ctorWithoutSyntaxCheck(usr.getEmail(),usr.getPassword()),new GymInfoBean(((Gym) usr).getGymName(), ((Gym) usr).getAddress(), ((Gym) usr).getCity(), ((Gym) usr).getGymName()));
        }
        throw new UserCastException();
        }

        public UserBean geyMyBean() throws DataFieldException, UserCastException {
        return getUserBean(user);
        }

    public UserTypes getUserType() {
        return userType;
    }

    public List<Exercise> getExcerciseList() throws UserCastException {
        if(userType!=UserTypes.pt && userType!=UserTypes.gym)throw new UserCastException();
        //return ((Trainer)user).getGym(
        return null;
    }

}
