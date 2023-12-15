package viewone.graphicalControllers.launcher;

import beans.*;
import controllers.UserAccessController;
import engineering.LoggedUserSingleton;
import exceptions.AlreadyLoggedUserException;
import exceptions.CostumException;
import exceptions.UserCastException;
import exceptions.dataException.DataFieldException;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Athlete;
import model.Gym;
import model.Trainer;
import model.User;
import model.record.Credentials;
import model.record.PersonalInfo;
import utils.MainStage;
import utils.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import viewone.graphicalControllers.pt.PTHomeGUIController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class LoginGUIController {


    @FXML
    private Button submitButton;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwField;
    @FXML
    private TextField passwordField;
    private String typeUser;


    @FXML
    public void goForward() throws IOException {
        UserAccessController controller=new UserAccessController();
        UserBean userBean=null;
        try {
            userBean=controller.login(CredentialsBean.ctorWithSyntaxCheck(emailField.getText(),passwordField.getText()));
        }catch(AlreadyLoggedUserException e){
            try {
                userBean = LoggedUserSingleton.getSingleton().geyMyBean();
            }catch(UserCastException|DataFieldException e2){
                e2.callMe(1);
                return;
            }
        }catch (CostumException e) {
            e.callMe(1);
            return;
        }

        if (userBean instanceof AthleteBean) {

            SwitchPage.setStage(MainStage.getStage(), "AthleteHome.fxml", "athlete", 1);
            //mettice loggedSingleton user

        } else if (userBean instanceof TrainerBean) {
            SwitchPage.setStage(MainStage.getStage(), "PTHome.fxml", "pt", 1);
            // Objects.requireNonNull(controller).setValue(trainer);
        } else if(userBean instanceof GymBean) {
            SwitchPage.setStage(MainStage.getStage(), "GymHome.fxml", "gym", 1);
        }

    }

    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(), "ChooseActor.fxml", "launcher", 1);
    }

    public void setValue(String user) {
        this.typeUser = user;
    }

    public void getInfo(MouseEvent event) {
        //TODO Connected with the LogoInfoButton
    }
}