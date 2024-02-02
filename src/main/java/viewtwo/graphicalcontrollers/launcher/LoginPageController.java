package viewtwo.graphicalcontrollers.launcher;

import beans.AthleteBean;
import beans.CredentialsBean;
import controllers.UserAccessController;
import engineering.LoggedUserSingleton;
import exceptions.AlreadyLoggedUserException;
import exceptions.NoUserFoundException;
import exceptions.dataException.DataFieldException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import utils.MainStage;
import utils.SwitchPage;
import viewtwo.engegnering.MainMenuSingleton;
import viewtwo.graphicalcontrollers.home.MainMenuController;
import viewtwo.popups.LoginPopUp;
import viewtwo.popups.UserRegistrationPopup;
import viewtwo.popups.abstracts.LoginPopUpInterface;
import viewtwo.popups.abstracts.UserRegistrationPopupInterface;

import java.io.IOException;

public class LoginPageController implements LoginPopUpInterface,UserRegistrationPopupInterface{

    @FXML
    Label errorLabel;
    @FXML
    public void createLoginPopup() throws IOException {
        LoginPopUp.getLoginPopup(this,"loginPopUp.fxml","popups",2);
    }
    public void createUserRegistrationPopup() throws IOException {
        UserRegistrationPopup.getRegistrationPopup(this,"userRegistrationPopUP.fxml","popups",2);
    }
    private void setErrorText(String error){
        errorLabel.setText(error);
    }
    @Override
    public void loginCredentialInserted(String email, String password, boolean remember) throws IOException {
        UserAccessController controller = new UserAccessController();
        try {
            controller.login(CredentialsBean.ctorWithSyntaxCheck(email, password),remember);
        } catch (DataFieldException | NoUserFoundException e) {
            e.callMe(2);
            return;
        } catch (AlreadyLoggedUserException e) {
            e.callMe(2);
        }
        MainMenuController a = (MainMenuController) SwitchPage.setStage(MainStage.getStage(), "mainMenu.fxml", "home", 2);
        MainMenuSingleton.createMainMenu(a);
        switch (LoggedUserSingleton.getSingleton().getUserType()) {
            case pt ->        MainMenuSingleton.getMainMenu().setActivity("ptHome.fxml", "pt");
            case gym ->         MainMenuSingleton.getMainMenu().setActivity("gymUsersHome.fxml", "gym");
            case athlete ->          MainMenuSingleton.getMainMenu().setActivity("atheleteHome.fxml", "athlete");
            case null, default ->  MainStage.getStage().close();

        }
    }

    @Override
    public void userRegistration(AthleteBean bean) {
        setErrorText("Registrazione effettuata con successo, effettuare login");
    }
}
