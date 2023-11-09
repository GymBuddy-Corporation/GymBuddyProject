package viewone.graphical_controllers.launcher.gym;

import utils.MainStage;
import utils.SwitchPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewone.GymBuddy;

public class GymFirstGUIController {
    //TODO sistema sti cazzo di pulsanti avanti indietro che non apparono
    @FXML
    protected void login() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"GymLogin.fxml","launcher",1);
    }

    @FXML protected void registration() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"GymRegistration.fxml","launcher",1);
    }

    @FXML public void getInfo() throws Exception {
        //TODO organizza il bottone info
    }

    @FXML public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"ChooseActor.fxml","launcher",1);
    }

}
