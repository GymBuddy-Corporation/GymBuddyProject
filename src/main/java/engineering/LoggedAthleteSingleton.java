package engineering;

import beans.AthleteBean;
import engineering.decorator.MembershipInterface;
import exceptions.AlreadyLoggedUserException;
import model.Athlete;

public class LoggedAthleteSingleton extends LoggedUserSingleton{
    protected LoggedAthleteSingleton(Athlete temp) {
        super(temp);
    }


    private MembershipInterface membership;

    public String getGymToSubscribe() {
        return gymToSubscribe;
    }

    private String gymToSubscribe;

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
