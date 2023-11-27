package viewtwo.popups;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Popup;
import utils.MainStage;
import utils.SwitchPage;
import viewtwo.popups.abstracts.LoginPopUpInterface;

import java.io.IOException;

public class LoginPopUp {

    LoginPopUpInterface caller;

    private Popup popup_reference;
    protected static LoginPopUp me;


    private LoginPopUp(LoginPopUpInterface instanceOfParent) throws IOException {
        this.caller=instanceOfParent;
        String path=SwitchPage.getpath("loginPopUp.fxml","popups",2);
        FXMLLoader fxmlLoader = new FXMLLoader(SwitchPage.class.getResource(path));
        Parent load = null;
        load = fxmlLoader.load();
        popup_reference=new Popup();
        popup_reference.getContent().add(load);
        popup_reference.show(MainStage.getStage());
        popup_reference.setAutoHide(true);
        popup_reference.setOnAutoHide(handler->{clearSingleton();});
    }


    public  static LoginPopUp getLoginPopup(LoginPopUpInterface caller) throws IOException {
            if(LoginPopUp.me==null){
                LoginPopUp.me=new LoginPopUp(caller);
            }
            return LoginPopUp.me;
    }
    private void clearSingleton(){
        popup_reference.hide();
        me.caller=null;
        me=null;
    }
    public void popUpConfirm(){
      //  caller.popUpConfirm();
    }
    public void popUpDelete(){
       // caller.popUpDelete();
    }
}
