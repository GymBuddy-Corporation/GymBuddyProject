package viewone.graphicalcontrollers.launcher;

import utils.MainStage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.SwitchPage;
import viewone.graphicalcontrollers.launcher.gym.GymFirstGUIController;

import java.net.URL;

import java.util.Objects;
import java.util.ResourceBundle;


public class ChooseActorGUIController implements Initializable {

    @FXML private ImageView gym;
    @FXML private ImageView pt;
    @FXML private ImageView athlete;
    private String user;
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
        ATHLETE
    }

    Selezione scelta;
    Image empty;
    Image full;



@FXML
    public void setBottonegym()throws  Exception{
        pt.setImage(empty);
        gym.setImage(full);
        athlete.setImage(empty);
        scelta=Selezione.GYM;
    }
    @FXML
    public void setBottonept()throws  Exception{
        pt.setImage(full);
        gym.setImage(empty);
        athlete.setImage(empty);
        scelta=Selezione.PT;
    }
    @FXML
    public void setBottoneuser()throws  Exception{
        pt.setImage(empty);
        gym.setImage(empty);
        athlete.setImage(full);
        scelta=Selezione.ATHLETE;
    }
    @FXML
    public void goForward() throws Exception {
        String path;
        if(scelta==Selezione.GYM) {
            path = "GymFirst.fxml";
            this.user = "gym";
            GymFirstGUIController controller = (GymFirstGUIController) SwitchPage.setStage(MainStage.getStage(),path,"launcher",1);
            Objects.requireNonNull(controller).setValue(user);
        } else if (scelta==Selezione.PT) {
            this.user = "trainer";
            path = "Login.fxml"; //TODO alla fine avremo un solo login ora serve solo per andare avanti senza DB
            LoginGUIController controller = (LoginGUIController) SwitchPage.setStage(MainStage.getStage(),path,"launcher",1);

        } else if(scelta==Selezione.ATHLETE) {
            this.user = "athlete";
            path = "Login.fxml"; //TODO alla fine avremo un solo login ora serve solo per andare avantisenza DB
            LoginGUIController controller = (LoginGUIController) SwitchPage.setStage(MainStage.getStage(),path,"launcher",1);
        }else{
            return;
        }
    }

    public void changeLanguage() {
        //TODO gestisci il bottone con il pop sulla scelta della lingua (il cambio è dummy)
    }


}

