package engineering;

import beans.*;
import controllers.LoginController;

import exceptions.dataException.DataFieldException;
import model.*;

import java.util.Arrays;
import java.util.List;

public class LoggedUserSingleton {



    private  User user;
    private static String tipo_utente;
    private static LoggedUserSingleton me;
    //private static UserInfoCarrier userInfoCarrier;
    private static final LoginController loginController = new LoginController();

    private LoggedUserSingleton(User temp) {
        user=temp;
    }

    public static User getUser() {
        if(me==null)return null;
        return me.user;
    }

    public static LoggedUserSingleton getUserSingleton(User temp){
        if(me.user==null){
            new LoggedUserSingleton(temp);
        }
        return me;
    }

    public  String getAthleteUsername() {
        return user.getUsername();
    }



    public  List<PersonBean> getAthleteAndTrainer() throws DataFieldException /*throws DBUnreachableException, SQLException, NoCardInsertedException*/ {
        Athlete usr = (Athlete) loginController.getLoggedUser();
        Trainer trainer = usr.getTrainer();
        AthleteBean athleteBean = new AthleteBean(
                usr.getUsername(),
                new PersonalInfoBean(
                        usr.getName(),
                        usr.getSurname(),
                        usr.getDateOfBirth(),
                        usr.getFC(),
                        usr.getGender()
                ),
                CredentialsBean.ctorWithoutSyntaxCheck(
                        usr.getEmail(),
                        usr.getPassword()
                )/*,
                new CardInfoBean(
                        usr.getCardNumber(),
                        usr.getCardExpirationDate()
                )*/);
        TrainerBean trainerBean = new TrainerBean(
                trainer.getUsername(),
                new PersonalInfoBean(
                        trainer.getName(),
                        trainer.getSurname(),
                        trainer.getDateOfBirth(),
                        trainer.getFC(),
                        trainer.getGender()
                ),
                CredentialsBean.ctorWithoutSyntaxCheck(
                        trainer.getEmail(),
                        trainer.getPassword()
                )/*,
                trainer.getIban()*/);
        return Arrays.asList(athleteBean, trainerBean);
    }

    public static UserBean getUserBean(User usr) throws DataFieldException {
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
        } else {
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
        }
    }

    /* public static UserBean getInstance() *//*throws DBUnreachableException*//*{
        try{
            User usr = loginController.getLoggedUser();
            return getUserBean(usr);
        } catch (SQLException e){
            e.printStackTrace();
            throw new FatalErrorException();
        }
    }*/

    /*public static void resetUserInfo() {
        userInfoCarrier = null;
    }*/
}
