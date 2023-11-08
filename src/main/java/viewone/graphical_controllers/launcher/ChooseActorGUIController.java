package viewone.graphical_controllers.launcher;
import utils.MainStage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.SwitchPage;

import java.net.URL;
import java.util.ResourceBundle;


public class ChooseActorGUIController implements Initializable {

    @FXML private ImageView gym;
    @FXML private ImageView pt;
    @FXML private ImageView user;
//TODO: devo trovare una soluzione per i radio button, li posso lasciare cosi oppure creare una classe che gli gestisce
    //Sono Luca e ho copiato qesto codice nei send communication
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            empty=new Image(ChooseActorGUIController.class.getResourceAsStream("/viewone/images/LogoEmptyButton96.png"),20,20,false,true);
            full=new Image(ChooseActorGUIController.class.getResourceAsStream("/viewone/images/LogoFullButton96.png"),20,20,false,true);


    }

    enum Selezione{
        GYM,
        PT,
        USER
    }

    Selezione scelta;
    Image empty;
    Image full;



@FXML
    public void setBottonegym()throws  Exception{
        pt.setImage(empty);
        gym.setImage(full);
        user.setImage(empty);
        scelta=Selezione.GYM;
    }
    @FXML
    public void setBottonept()throws  Exception{
        pt.setImage(full);
        gym.setImage(empty);
        user.setImage(empty);
        scelta=Selezione.PT;
    }
    @FXML
    public void setBottoneuser()throws  Exception{
        pt.setImage(empty);
        gym.setImage(empty);
        user.setImage(full);
        scelta=Selezione.USER;
    }
    @FXML
    public void goForward() throws Exception {
        String path;
        if(scelta==Selezione.GYM) {
            path = "GymFirst.fxml";
        } else if (scelta==Selezione.PT) {
             path = "PTLogin.fxml"; //TODO alla fine avremo un solo login ora serve solo per andare avantisenza DB
        } else if(scelta==Selezione.USER) {
             path = "AthleteLogin.fxml"; //TODO alla fine avremo un solo login ora serve solo per andare avantisenza DB
        }else{return;}
        SwitchPage.setStage(MainStage.getStage(),path,"launcher",1);
    }

    public void changeLanguage() {
        //TODO gestisci il bottone con il pop sulla scelta della lingua (il cambio è dummy)
    }


}

