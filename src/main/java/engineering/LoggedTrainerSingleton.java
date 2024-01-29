package engineering;

import beans.TrainerBean;
import exceptions.AlreadyLoggedUserException;
import model.Exercise;
import model.Trainer;

import java.util.List;

public class LoggedTrainerSingleton extends LoggedUserSingleton{
    protected LoggedTrainerSingleton(Trainer temp) {
        super(temp);
    }

    @Override
    public Trainer getUser(){
        return (Trainer) user;
    }
    @Override
    public TrainerBean getMyBean() {
        return (TrainerBean) getUserBean(user);
    }
    public static LoggedTrainerSingleton getSingleton(){
        return (LoggedTrainerSingleton) me;
    }
    public static LoggedTrainerSingleton createTrainerSingleton(Trainer trainer) throws AlreadyLoggedUserException {
        if(me==null){
            me= new LoggedTrainerSingleton(trainer);
        }else{
            throw new AlreadyLoggedUserException();
        }
        return (LoggedTrainerSingleton) me;
    }

    public String getGym(){
        return  ((Trainer)user).getGym().getGymName();
    }

    public List<Exercise> getExcerciseList(){
        return  ((Trainer)user).getGym().getGymExercises().getExerciseList();
    }


}
