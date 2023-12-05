package viewtwo.graphicalcontrollers.launcher;

import javafx.scene.Node;
import javafx.scene.Scene;
import utils.EmailValdator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import utils.MainStage;
import utils.SwitchPage;
import viewtwo.engegnering.MainMenuSingleton;
import viewtwo.graphicalcontrollers.home.MainMenuController;
import viewtwo.popups.LoginPopUp;
import viewtwo.popups.abstracts.LoginPopUpInterface;

import java.io.IOException;

public class LoginPageController implements LoginPopUpInterface {
    @Override
    public void invokePopUp() throws IOException {
        LoginPopUp.getLoginPopup(this,"loginPopUp.fxml","popups",2);
    }
    @FXML
    Label errorLabel;
    @FXML
    public void createPopUp() throws IOException {
        this.invokePopUp();
    }

    private void setErrorText(String error){
        errorLabel.setText(error);
    }
    @Override
    public void loginCredentialInserted(String email, String password, boolean remember) throws IOException {
        if(EmailValdator.isEmailValid(email)!=true){
            setErrorText("Email non valida");
        }
                //TODO: effettuare la verifica effettiva delle credenziali
        //capire quale tipologia di utente sta effettuando il logni e inserire nel manel del menu la home giusta, per ora faro semplicemente attraverso l'email utilizzando i nomi gym,user e pt
        MainMenuController a=(MainMenuController)SwitchPage.setStage(MainStage.getStage(),"mainMenu.fxml","home",2);
        MainMenuSingleton.createMainMenu(a);
        MainMenuSingleton.getMainMenu().setActivity("gymUsersHome.fxml","gym");
    }
}
