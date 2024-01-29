package engineering;

import beans.GymBean;
import exceptions.AlreadyLoggedUserException;
import model.Exercise;
import model.Gym;

import java.util.List;

public class LoggedGymSingleton extends  LoggedUserSingleton{

    protected LoggedGymSingleton(Gym temp) {
        super(temp);
    }

    @Override
    public Gym getUser(){
        return (Gym)user;
    }
    @Override
    public GymBean getMyBean() {
        return (GymBean) getUserBean(user);
    }
    public static LoggedGymSingleton getSingleton(){
        return (LoggedGymSingleton) me;
    }
    public static LoggedGymSingleton createGymSingleton(Gym gym) throws AlreadyLoggedUserException {
        if(me==null){
            me= new LoggedGymSingleton(gym);
        }else{
            throw new AlreadyLoggedUserException();
        }
        return (LoggedGymSingleton) me;
    }

    public List<Exercise> getExcerciseList(){
        return  ((Gym)user).getGymExercises().getExerciseList();
    }


}
