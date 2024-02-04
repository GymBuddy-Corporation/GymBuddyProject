package engineering;

import beans.AthleteBean;
import engineering.decorator.MembershipInterface;
import exceptions.AlreadyLoggedUserException;
import model.Athlete;

public class LoggedAthleteSingleton extends LoggedUserSingleton{
    protected LoggedAthleteSingleton(Athlete temp) {
        super(temp);
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
