package viewone.graphicalControllers.launcher;

import beans.*;
import controllers.UserAccessController;
import engineering.LoggedUserSingleton;
import exceptions.AlreadyLoggedUserException;
import exceptions.NoUserFoundException;
import exceptions.dataException.DataFieldException;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utils.MainStage;
import utils.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import java.sql.SQLException;


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
    public void goForward() throws IOException  {
        UserAccessController controller=new UserAccessController();
        UserBean userBean=null;
        try {
            try {
                userBean=controller.login(CredentialsBean.ctorWithSyntaxCheck(emailField.getText(),passwordField.getText()));
            } catch (DataFieldException|NoUserFoundException e) {
                e.callMe(1);
                return;
            }
        }catch(AlreadyLoggedUserException e){
            userBean = LoggedUserSingleton.getSingleton().getMyBean();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       changePage(userBean);

    }
    private void changePage(UserBean userBean) throws IOException {
        if (userBean instanceof AthleteBean) {
            SwitchPage.setStage(MainStage.getStage(), "AthleteHome.fxml", "athlete", 1);
        } else if (userBean instanceof TrainerBean) {
            SwitchPage.setStage(MainStage.getStage(), "PTHome.fxml", "pt", 1);
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