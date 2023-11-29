package viewtwo.popups;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Popup;
import utils.MainStage;
import utils.SwitchPage;
import viewtwo.popups.abstracts.LoginPopUpInterface;
import viewtwo.popups.controllers.LoginPopUpController;

import java.io.IOException;

public class LoginPopUp {

    LoginPopUpInterface caller;

    private Popup popupReference;
    protected static LoginPopUp me;


    private LoginPopUp(LoginPopUpInterface instanceOfParent) throws IOException {
        this.caller=instanceOfParent;
        String path=SwitchPage.getpath("loginPopUp.fxml","popups",2);
        FXMLLoader fxmlLoader = new FXMLLoader(SwitchPage.class.getResource(path));
        Parent load = null;
        load = fxmlLoader.load();
        popupReference =new Popup();
        popupReference.getContent().add(load);
        ((LoginPopUpController)fxmlLoader.getController()).setCaller(this);
        popupReference.show(MainStage.getStage());
        popupReference.setAutoHide(true);
        popupReference.setOnAutoHide(handler->{clearSingleton();});
    }


    public  static LoginPopUp getLoginPopup(LoginPopUpInterface caller) throws IOException {
            if(LoginPopUp.me==null){
                LoginPopUp.me=new LoginPopUp(caller);
            }
            return LoginPopUp.me;
    }
    private static void clearSingleton(){
        me.caller=null;
        me=null;
    }
    public void hidePopUp(){
        popupReference.hide();
    }
    public void doLogin(String email,String password) throws IOException {
        caller.loginCredentialInserted(email,password,true);
        hidePopUp();
        LoginPopUp.clearSingleton();
    }
}
