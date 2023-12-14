package viewone.graphicalControllers.launcher;

import controllers.LoginController;
import engineering.LoggedUserSingleton;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Athlete;
import model.Gym;
import model.Trainer;
import model.record.Credentials;
import model.record.PersonalInfo;
import utils.MainStage;
import utils.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import viewone.graphicalControllers.pt.PTHomeGUIController;

import java.time.LocalDate;
import java.util.Objects;

public class LoginGUIController {

    @FXML private Button submitButton;
    @FXML private TextField emailField;
    @FXML private TextField passwField;
    @FXML private TextField passwSField;
    private String typeUser;

    private final LoginController loginController = new LoginController();
    @FXML
    public void goForward() throws Exception {
        /*try {*/
        //LoggedUserSingleton.resetUserInfo();
        Gym palestra1 = new Gym("palestra1", new Credentials("alecortix@gmail.com", "forzanapule1926"),
                "BBBBBBBBBBBBBBBBBBBBBB", "roma", "Piazza dei Consoli, 11");
        Trainer trainer= new Trainer("AleCortix",
                new PersonalInfo("Alessandro", "Cortese", LocalDate.now(), "CRTLSN99T24H501R", 'm'),
                new Credentials("alecortix@gmail.com", "forzanapule1926"), palestra1);
        /*UserBean user = loginController.login(CredentialsBean.ctorWithSyntaxCheck(emailField.getText(), passwField.getText()));
                LoggedUserSingleton.setFc(user.getPersonalInfo().getFc());*/
        if(/*getLoggedUser() instanceof AthleteBean*/ typeUser.equals("athlete")){
            //LoggedUserSingleton.setAthleteUsername("LuX");
            PersonalInfo pi1 = new PersonalInfo("Luca", "Martorelli", LocalDate.of(2000, 9, 1), "MRTLCU00P01D612J", 'm');
            Credentials credentials1 = new Credentials("lucam0109@gmail.com", "LAZINESS1900");
            Athlete athlete1 = new Athlete("LuX71", pi1,
                    credentials1, palestra1, trainer);
            SwitchPage.setStage(MainStage.getStage(),"AthleteHome.fxml","athlete",1);
            //mettice loggedSingleton user

        } else if (/*getLoggedUser() instanceof TrainerBean*/ typeUser.equals("trainer")){
            PTHomeGUIController controller = (PTHomeGUIController) SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
            Objects.requireNonNull(controller).setValue(trainer);
        } else {
            SwitchPage.setStage(MainStage.getStage(),"GymHome.fxml","gym",1);
        }
       /* } catch (DBUnreachableException | InvalidDataException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        } catch (UserNotFoundException e) {
            AlertGenerator.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                    "User not found.",
                    "Be sure that you have an account on WeTrain.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }*/
    }

    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"ChooseActor.fxml","launcher",1);
    }

    public void setValue(String user){
        this.typeUser = user;
    }

    public void getInfo(MouseEvent event){
        //TODO Connected with the LogoInfoButton
    }
}