package viewone.graphicalControllers.launcher.gym;

import utils.MainStage;
import utils.SwitchPage;
import javafx.fxml.FXML;

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
