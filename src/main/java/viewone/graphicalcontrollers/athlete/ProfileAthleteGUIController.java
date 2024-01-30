package viewone.graphicalcontrollers.athlete;

import controllers.UserAccessController;
import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

public class ProfileAthleteGUIController {
    private final String athlete = "athlete";
    private final String launcher = "launcher";

    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"AthleteHome.fxml",athlete,1);
    }
    @FXML
    public void logout() throws Exception {
        UserAccessController controller=new UserAccessController();
        controller.logout();
        SwitchPage.setStage(MainStage.getStage(),"Login.fxml","launcher",1);
    }
    @FXML
    public void deleteAccount() throws Exception {
        //TODO gestisci l'eliminazione dell'account con notifiche e pop-up
        SwitchPage.setStage(MainStage.getStage(),"AthleteLogin.fxml",launcher,1);
    }
    @FXML
    public void editProfile() throws Exception {
        //TODO gestisci la gestione dell'account
        SwitchPage.setStage(MainStage.getStage(),"AthletePT.fxml",athlete,1);
    }
}
