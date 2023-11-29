package engineering;

import beans.*;
import controllers.LoginController;

import model.Person;
import model.Trainer;
import model.User;
import model.Athlete;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LoggedUserSingleton {

    private static String fc;
    //private static UserInfoCarrier userInfoCarrier;
    private static final LoginController loginController = new LoginController();

    private LoggedUserSingleton() {}

    public static String getFc() {
        return fc;
    }

    public static void setFc(String fc) {
        LoggedUserSingleton.fc = fc;
    }

    /*public static List<UserBean> getAthleteAndTrainer() *//*throws DBUnreachableException, SQLException, NoCardInsertedException*//* {
        Athlete usr = new Athlete("provaAtleta", , )*//*(Athlete) loginController.getLoggedUser()*//*;
        Trainer trainer = usr.getTrainer();
        AthleteBean athleteBean = new AthleteBean(
                usr.getUsername(),
                new PersonalInfoBean(
                        usr.getName(),
                        usr.getSurname(),
                        usr.getDateOfBirth(),
                        usr.getFiscalCode(),
                        usr.getGender()
                ),
                CredentialsBean.ctorWithoutSyntaxCheck(
                        usr.getEmail(),
                        usr.getPassword()
                )*//*,
                new CardInfoBean(
                        usr.getCardNumber(),
                        usr.getCardExpirationDate()
                )*//*);
        TrainerBean trainerBean = new TrainerBean(
                trainer.getUsername(),
                new PersonalInfoBean(
                        trainer.getName(),
                        trainer.getSurname(),
                        trainer.getDateOfBirth(),
                        trainer.getFiscalCode(),
                        trainer.getGender()
                ),
                CredentialsBean.ctorWithoutSyntaxCheck(
                        trainer.getEmail(),
                        trainer.getPassword()
                )*//*,
                trainer.getIban()*//*);
        return Arrays.asList(athleteBean, trainerBean);
    }*/

    public static UserBean getUserBean(User usr){
        if (usr instanceof Athlete) {
            /*try {*/
                return new AthleteBean(
                        usr.getUsername(),
                        new PersonalInfoBean(
                                ((Person) usr).getName(),
                                ((Person) usr).getSurname(),
                                ((Person) usr).getDateOfBirth(),
                                ((Person) usr).getFC(),
                                ((Person) usr).getGender()
                        ),
                        CredentialsBean.ctorWithoutSyntaxCheck(
                                usr.getEmail(),
                                usr.getPassword()
                        )/*,
                        new CardInfoBean(
                                ((Athlete) usr).getCardNumber(),
                                ((Athlete) usr).getCardExpirationDate()
                        )*/);
            /*} catch (NoCardInsertedException e) {
                return new AthleteBean(
                        usr.getUsername(),
                        new PersonalInfoBean(
                                usr.getName(),
                                usr.getSurname(),
                                usr.getDateOfBirth(),
                                usr.getFiscalCode(),
                                usr.getGender()
                        ),
                        CredentialsBean.ctorWithoutSyntaxCheck(
                                usr.getEmail(),
                                usr.getPassword()
                        ),
                        new CardInfoBean(
                                null, (YearMonth) null
                        ));
            }*/
        } else {
            return new TrainerBean(
                    usr.getUsername(),
                    new PersonalInfoBean(
                            ((Person) usr).getName(),
                            ((Person) usr).getSurname(),
                            ((Person) usr).getDateOfBirth(),
                            ((Person) usr).getFC(),
                            ((Person) usr).getGender()
                    ),
                    CredentialsBean.ctorWithoutSyntaxCheck(
                            usr.getEmail(),
                            usr.getPassword()
                    ));
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
