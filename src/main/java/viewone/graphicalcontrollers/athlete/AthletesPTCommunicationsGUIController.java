package viewone.graphicalcontrollers.athlete;

import controllers.UserAccessController;
import exceptions.logger.CostumeLogger;
import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

import java.io.IOException;

public class AthletesPTCommunicationsGUIController {
    private final String athlete = "athlete";

    @FXML
    public void goBack() {
        try {
            SwitchPage.setStage(MainStage.getStage(),"AthleteHome.fxml",athlete,1);
        } catch (IOException e){
            CostumeLogger.getInstance().logError(e);
        }
    }

    @FXML
    public void openCommunication(){
        //TODO apri la schermata con la comunicazione
        try {
            UserAccessController controller=new UserAccessController();
            controller.logout();
            SwitchPage.setStage(MainStage.getStage(),"Login.fxml","launcher",1);        } catch (IOException e){
        } catch (Exception e){
            CostumeLogger.getInstance().logError(e);
        }
    }

    @FXML
    public void logout(){
        try {
            UserAccessController controller = new UserAccessController();
            controller.logout();
            SwitchPage.setStage(MainStage.getStage(), "Login.fxml", "launcher", 1);
        } catch (IOException e){
            CostumeLogger.getInstance().logError(e);
        }
    }

    @FXML
    public void openProfile(){
        try {
            SwitchPage.setStage(MainStage.getStage(),"ProfileAthlete.fxml",athlete,1);
        } catch (IOException e){
            CostumeLogger.getInstance().logError(e);
        }
    }

}
