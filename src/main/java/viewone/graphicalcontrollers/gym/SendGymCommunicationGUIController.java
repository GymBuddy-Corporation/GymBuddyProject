package viewone.graphicalcontrollers.gym;

import controllers.UserAccessController;
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

    private static final String GYM="gym";
    private static final String LAUNCHER="launcher";

    private static final int VIEW=1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        empty=new Image(SendGymCommunicationGUIController.class.getResourceAsStream("/viewone/images/LogoEmptyButton96.png"),20,20,false,true);
        full=new Image(SendGymCommunicationGUIController.class.getResourceAsStream("/viewone/images/LogoFullButton96.png"),20,20,false,true);


    }

    SendGymCommunicationGUIController.Selezione scelta;
    Image empty;
    Image full;

    enum Selezione{
        PT,
        USER
    }


    @FXML
    public void logout()  {
        UserAccessController controller=new UserAccessController();
        controller.logout();
        SwitchPage.changePage("Login.fxml",LAUNCHER,VIEW);
    }
    @FXML
    public void setBottonept() {
        pt.setImage(full);
        user.setImage(empty);
        scelta= SendGymCommunicationGUIController.Selezione.PT;
    }
    @FXML
    public void sendCommunication()  {
        SwitchPage.changePage("GymHome.fxml",GYM,VIEW);
    }
    @FXML
    public void goBack()  {
        SwitchPage.changePage("GymHome.fxml",GYM,VIEW);
    }
    @FXML
    public void setBottoneuser() {
        pt.setImage(empty);
        user.setImage(full);
        scelta= SendGymCommunicationGUIController.Selezione.USER;
    }
    @FXML
    public void goForward()  {
        String path;
        if (scelta== SendGymCommunicationGUIController.Selezione.PT) {
            path = "Login.fxml";
        } else if(scelta== SendGymCommunicationGUIController.Selezione.USER) {
            path = "AthleteLogin.fxml";
        }else{return;}
        SwitchPage.changePage(path,GYM,VIEW);
    }

}
