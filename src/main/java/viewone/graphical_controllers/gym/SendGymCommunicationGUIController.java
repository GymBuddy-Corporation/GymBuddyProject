package viewone.graphical_controllers.gym;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.MainStage;
import utils.SwitchPage;

import java.net.URL;
import java.util.ResourceBundle;

public class SendGymCommunicationGUIController implements Initializable {

    @FXML
    private ImageView user;
    @FXML private ImageView pt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        empty=new Image(SendGymCommunicationGUIController.class.getResourceAsStream("/viewone/images/LogoEmptyButton96.png"),20,20,false,true);
        full=new Image(SendGymCommunicationGUIController.class.getResourceAsStream("/viewone/images/LogoFullButton96.png"),20,20,false,true);


    }

    SendGymCommunicationGUIController.Selezione scelta;
    Image empty;
    Image full;

    enum Selezione{//TODO: Da rimuovere i bottoni radio belli e implementarli con una classe
        PT,
        USER
    }


    @FXML
    public void logout() throws Exception {
        String path = "/viewone/launcher/GymFirst.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }
    @FXML
    public void setBottonept()throws  Exception{
        pt.setImage(full);
        user.setImage(empty);
        scelta= SendGymCommunicationGUIController.Selezione.PT;
    }
    @FXML
    public void sendCommunication() throws Exception {
        String path = "/viewone/gym/GymHome.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }
    @FXML
    public void goBack() throws Exception {
        String path = "/viewone/gym/GymHome.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }
    @FXML
    public void setBottoneuser()throws  Exception{
        pt.setImage(empty);
        user.setImage(full);
        scelta= SendGymCommunicationGUIController.Selezione.USER;
    }
    @FXML
    public void goForward() throws Exception {
        String path;
        if (scelta== SendGymCommunicationGUIController.Selezione.PT) {
            path = "/viewone/launcher/PTLogin.fxml"; //TODO alla fine avremo un solo login ora serve solo per andare avantisenza DB
        } else if(scelta== SendGymCommunicationGUIController.Selezione.USER) {
            path = "/viewone/launcher/AthleteLogin.fxml"; //TODO alla fine avremo un solo login ora serve solo per andare avantisenza DB
        }else{return;}
        SwitchPage.setStage(MainStage.getStage(),path);
    }
}
