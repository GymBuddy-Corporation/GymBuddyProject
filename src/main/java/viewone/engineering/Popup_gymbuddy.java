package viewone.engineering;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.MainStage;

public class Popup_gymbuddy extends Application {
    String testo;
    String conferma;
    String rifiuto;
    Popup_abstract caller;


    static Popup_gymbuddy me;
    private Popup_gymbuddy(Popup_abstract a,String testo) {

        this(a,testo,"conferma","rifiuta");
    }
    private Popup_gymbuddy(Popup_abstract a,String testo,String conferma,String rifiuto){
        this.testo=testo;
        this.conferma=conferma;
        this.rifiuto=rifiuto;
        this.caller=a;
        //MainStage.getStage(). settare lo stage a
        launch();
    }

    public static void startPopUp(Popup_abstract a,String testo,String conferma,String rifiuto){
           if(Popup_gymbuddy.me!=null){
               return; // TODO aggiungere un exception per popup gia esistente
           }else{
               Popup_gymbuddy.me=new Popup_gymbuddy(a,testo,conferma,rifiuto);
           }
    }
    public static void startPopUp(Popup_abstract a,String testo){
        if(Popup_gymbuddy.me!=null){
            return;
        }else{
            Popup_gymbuddy.me=new Popup_gymbuddy(a,testo);
        }
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("GymBuddy");
        stage.setResizable(false);
        stage.show();
    }


}
