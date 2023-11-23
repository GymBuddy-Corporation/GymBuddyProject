package viewone.engineering;

import javafx.application.Application;
import javafx.stage.Stage;
import utils.SwitchPage;

public class Popup_gymbuddy extends Application {
    String testo;
    String conferma;
    String rifiuto;
    Popup_abstract caller;


    static Popup_gymbuddy me;

    private Popup_gymbuddy(Popup_abstract a,String testo,String conferma,String rifiuto){
        this.testo=testo;
        this.conferma=conferma;
        this.rifiuto=rifiuto;
        this.caller=a;
        //MainStage.getStage(). settare lo stage a
        launch();
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

    @Override
    public void start(Stage stage) throws Exception {
        SwitchPage.setStage(stage,"ConfirmSubmitRoutine.fxml","pt",1);
        stage.setTitle("GymBuddy");
        stage.setResizable(false);
        stage.show();
    }


}
