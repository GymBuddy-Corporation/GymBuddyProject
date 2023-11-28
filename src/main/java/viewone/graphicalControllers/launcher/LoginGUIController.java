package viewone.graphicalControllers.launcher;

import beans.AthleteBean;
import beans.CredentialsBean;
import beans.TrainerBean;
import beans.UserBean;
import controllers.LoginController;
import engineering.LoggedUserSingleton;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utils.MainStage;
import utils.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import viewone.graphicalControllers.pt.PTHomeGUIController;
import viewone.graphicalControllers.pt.SatisfyWorkoutRoutineRequestGUIController;

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

        /*UserBean user = loginController.login(CredentialsBean.ctorWithSyntaxCheck(emailField.getText(), passwField.getText()));
                LoggedUserSingleton.setFc(user.getPersonalInfo().getFc());*/
        if(/*getLoggedUser() instanceof AthleteBean*/ typeUser.equals("athlete")){
            SwitchPage.setStage(MainStage.getStage(),"AthleteHome.fxml","athlete",1);
        } else if (/*getLoggedUser() instanceof TrainerBean*/ typeUser.equals("trainer")){
            PTHomeGUIController controller = (PTHomeGUIController) SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
            Objects.requireNonNull(controller).setValue(typeUser);
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