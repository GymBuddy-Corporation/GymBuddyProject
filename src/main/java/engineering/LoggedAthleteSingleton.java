package engineering;

import beans.AthleteBean;
import beans.TrainerBean;
import engineering.decorator.MembershipInterface;
import exceptions.AlreadyLoggedUserException;
import model.Athlete;
import model.Exercise;
import model.Trainer;

import java.util.List;

public class LoggedAthleteSingleton extends LoggedUserSingleton{
    protected LoggedAthleteSingleton(Athlete temp) {
        super(temp);
    }


    private MembershipInterface membership;
    private String gymToSubscribe;

    public String getGymToSubscribe() {
        return gymToSubscribe;
    }

    public MembershipInterface getMembership() {
        return membership;
    }

    public void setMembership(MembershipInterface membership,String name) {
        this.gymToSubscribe=name;
        this.membership = membership;
    }

    @Override
    public Athlete getUser(){
        return (Athlete) user;
    }
    @Override
    public AthleteBean getMyBean() {
        return (AthleteBean) getUserBean(user);
    }
    public static LoggedAthleteSingleton getSingleton(){
        return (LoggedAthleteSingleton) me;
    }
    public static LoggedAthleteSingleton createAthleteSingleton(Athlete athlete) throws AlreadyLoggedUserException {
        if(me==null){
            me= new LoggedAthleteSingleton(athlete);
        }else{
            throw new AlreadyLoggedUserException();
        }
        return (LoggedAthleteSingleton) me;
    }
}
