package engineering;

import beans.GymBean;
import beans.UserBean;
import com.mysql.cj.log.Log;
import exceptions.AlreadyLoggedUserException;
import exceptions.dataException.DataFieldException;
import model.Exercise;
import model.Gym;
import model.Trainer;
import model.User;

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
