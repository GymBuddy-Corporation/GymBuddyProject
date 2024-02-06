package viewtwo.graphicalcontrollers.launcher;

import beans.AthleteBean;
import beans.CredentialsBean;
import controllers.UserAccessController;
import exceptions.AlreadyLoggedUserException;
import exceptions.DBUnrreachableException;
import exceptions.NoUserFoundException;
import exceptions.dataexception.DataFieldException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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

    @Override
    public void loginCredentialInserted(String email, String password, boolean remember) throws IOException {
        UserAccessController controller = new UserAccessController();
        try {
            controller.login(CredentialsBean.ctorWithSyntaxCheck(email, password),remember);
        } catch (NoUserFoundException | DataFieldException | DBUnrreachableException e) {
            e.callMe(2);
            return;
        } catch (AlreadyLoggedUserException e) {
            e.callMe(2);
        }
        WelcomeController.afterLogin();
    }

    @Override
    public void userRegistration(AthleteBean bean) {
        setErrorText("Registrazione effettuata con successo, effettuare login");
    }

    private void setErrorText(String error){
        errorLabel.setText(error);
    }
}
