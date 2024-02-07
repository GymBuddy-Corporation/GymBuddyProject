package viewone.graphicalcontrollers.launcher;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.MainStage;
import utils.SwitchPage;

import java.net.URL;
import java.util.ResourceBundle;


public class ChooseActorGUIController implements Initializable {

    public static final String LAUNCHER_FOLDER ="launcher";
    Selezione scelta;
    Image empty;
    Image full;
    @FXML private ImageView gym;
    @FXML private ImageView pt;
    @FXML private ImageView athlete;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            empty=new Image(ChooseActorGUIController.class.getResourceAsStream("/viewone/images/LogoEmptyButton96.png"),20,20,false,true);
            full=new Image(ChooseActorGUIController.class.getResourceAsStream("/viewone/images/LogoFullButton96.png"),20,20,false,true);
    }

@FXML
    public void setBottonegym() {
        pt.setImage(empty);
        gym.setImage(full);
        athlete.setImage(empty);
        scelta=Selezione.GYM;
    }

    @FXML
    public void setBottonept() {
        pt.setImage(full);
        gym.setImage(empty);
        athlete.setImage(empty);
        scelta=Selezione.PT;
    }

    @FXML
    public void setBottoneuser() {
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

            SwitchPage.setStage(MainStage.getStage(),path, LAUNCHER_FOLDER,1);
        } else if (scelta==Selezione.PT) {

            path = "Login.fxml";
           SwitchPage.setStage(MainStage.getStage(),path, LAUNCHER_FOLDER,1);

        } else if(scelta==Selezione.ATHLETE) {

            path = "Login.fxml";
            SwitchPage.setStage(MainStage.getStage(),path, LAUNCHER_FOLDER,1);
        }
    }
    enum Selezione{
        GYM,
        PT,
        ATHLETE
    }

}

