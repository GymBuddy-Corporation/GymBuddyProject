package engineering;

import beans.*;
import controllers.LoginController;

import exceptions.dataException.DataFieldException;
import model.Person;
import model.User;
import model.Athlete;

public class LoggedUserSingleton {

    private static String athleteUsername;
    private static String trainerUsername;
    private static String gymUsername;

    //private static UserInfoCarrier userInfoCarrier;
    private static final LoginController loginController = new LoginController();

    private LoggedUserSingleton() {}

    public static String getAthleteUsername() {
        return athleteUsername;
    }

    public static void setAthleteUsername(String athleteUsername) {
        LoggedUserSingleton.athleteUsername = athleteUsername;
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

    public static String getTrainerUsername() {
        return trainerUsername;
    }

    public static void setTrainerUsername(String trainerUsername) {
        LoggedUserSingleton.trainerUsername = trainerUsername;
    }

    public static String getGymUsername() {
        return gymUsername;
    }

    public static void setGymUsername(String gymUsername) {
        LoggedUserSingleton.gymUsername = gymUsername;
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
