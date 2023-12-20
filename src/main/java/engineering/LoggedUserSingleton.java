package engineering;

import beans.*;

import exceptions.AlreadyLoggedUserException;
import exceptions.UserCastException;
import exceptions.dataException.DataFieldException;
import model.*;

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

    public <T extends User> T getSpecificUser(Class<T> userType) {
        if (userType.isInstance(user)) {
            return userType.cast(user);
        } else {
            throw new IllegalArgumentException("Requested user type does not match the actual user type.");
        }
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


    public static UserBean getUserBean(User usr) throws DataFieldException{
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
                            athlete.getPassword()));
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
                            gym.getGymName()
                    )
            );
        }
        return null;
    }

        public UserBean getMyBean() throws DataFieldException {
        return getUserBean(user);
        }

    public UserTypes getUserType() {
        return userType;
    }

    public ExerciseInventory getExcerciseInventory() {
        return ((Trainer)user).getGym().getGymExercises();

    }

}
