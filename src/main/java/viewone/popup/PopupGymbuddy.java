package viewone.popup;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Popup;
import utils.MainStage;
import utils.SwitchPage;

import java.io.IOException;

public class PopupGymbuddy {
    String testo;
    String conferma;
    String rifiuto;
    PopupAbstract caller;

    private Popup popup_reference;
    private static PopupGymbuddy me;


    private PopupGymbuddy(PopupAbstract a, String testo, String conferma, String rifiuto) throws IOException {
        this.testo=testo;
        this.conferma=conferma;
        this.rifiuto=rifiuto;
        this.caller=a;
        String path=SwitchPage.getpath("ConfirmSubmitRoutine.fxml","pt",1);
        FXMLLoader fxmlLoader = new FXMLLoader(SwitchPage.class.getResource(path));
        Parent load = null;
        load = fxmlLoader.load();
        //Scene scena = new Scene(load, 100, 100);
        popup_reference=new Popup();
        popup_reference.getContent().add(load);
        ((PopupController)fxmlLoader.getController()).setValues(testo,conferma,rifiuto);
        popup_reference.show(MainStage.getStage());
        popup_reference.setAutoHide(true);
        popup_reference.setOnAutoHide(handler->{clearSingleton();});
    }

    public static PopupGymbuddy startPopUp(PopupAbstract a, String testo, String conferma, String rifiuto) throws IOException {
           if(PopupGymbuddy.me==null){
               PopupGymbuddy.me=new PopupGymbuddy(a,testo,conferma,rifiuto);
           }
           return me;
    }
    public static PopupGymbuddy startPopUp(PopupAbstract a, String testo) throws IOException {

          return  PopupGymbuddy.startPopUp(a,testo,"conferma","rifiuta");
    }

    public static PopupGymbuddy getPopUp(){
        return me;
    }
    public void clearSingleton(){
        popup_reference.hide();
        me.caller=null;
        me=null;
    }
    public void popUpConfirm(){
        caller.popUpConfirm();
    }
    public void popUpDelete(){
        caller.popUpDelete();
    }
}
