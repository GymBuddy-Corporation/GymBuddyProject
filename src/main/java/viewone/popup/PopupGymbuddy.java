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

    private final Popup popupReference;
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
        popupReference =new Popup();
        popupReference.getContent().add(load);
        ((PopupController)fxmlLoader.getController()).setValues(testo,conferma,rifiuto);
        popupReference.show(MainStage.getStage());
        popupReference.setAutoHide(true);
        popupReference.setOnAutoHide(handler->{clearSingleton();});
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

    public void hidePopUp(){
        popupReference.hide();
    }
    public static PopupGymbuddy getPopUp(){
        return me;
    }
    public static void clearSingleton(){
        me.caller=null;
        me=null;
    }
    public void popUpConfirm() throws IOException{
        caller.popUpConfirm();
    }
    public void popUpDelete() {
        caller.popUpDelete();
    }
}
