package viewone.engineering;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import utils.MainStage;
import utils.SwitchPage;

import java.io.IOException;

public class Popup_gymbuddy {
    String testo;
    String conferma;
    String rifiuto;
    Popup_abstract caller;

    private Popup popup_reference;
    private static Popup_gymbuddy me;


    private Popup_gymbuddy(Popup_abstract a,String testo,String conferma,String rifiuto) {
        this.testo=testo;
        this.conferma=conferma;
        this.rifiuto=rifiuto;
        this.caller=a;
        String path=SwitchPage.getpath("ConfirmSubmitRoutine.fxml","pt",1);
        FXMLLoader fxmlLoader = new FXMLLoader(SwitchPage.class.getResource(path));
        Parent load = null;
        try{load = fxmlLoader.load();}catch (IOException e){System.out.println(e.getMessage());System.exit(-1);};
        //Scene scena = new Scene(load, 100, 100);
        popup_reference=new Popup();
        popup_reference.getContent().add(load);
        ((Popup_controller)fxmlLoader.getController()).setValues(testo,conferma,rifiuto);
        popup_reference.show(MainStage.getStage());
    }

    public static Popup_gymbuddy startPopUp(Popup_abstract a,String testo,String conferma,String rifiuto){
           if(Popup_gymbuddy.me==null){
               Popup_gymbuddy.me=new Popup_gymbuddy(a,testo,conferma,rifiuto);
           }
           return me;
    }
    public static Popup_gymbuddy startPopUp(Popup_abstract a,String testo){

          return  Popup_gymbuddy.startPopUp(a,testo,"conferma","rifiuta");
    }

    public static Popup_gymbuddy getPopUp(){
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
