package viewone.graphicalControllers.pt;

import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

public class ProfilePTGUIControllerGUIController {

    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }
    @FXML
    public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"PTLogin.fxml","launcher",1);
    }
    @FXML
    public void deleteAccount() throws Exception {
        //TODO gestisci l'eliminazione dell'account con notifiche e pop-up
        SwitchPage.setStage(MainStage.getStage(),"PTLogin.fxml","launcher",1);
    }
    @FXML
    public void editProfile() throws Exception {
        //TODO gestisci la gestione dell'account
        SwitchPage.setStage(MainStage.getStage(),"ProfilePT.fxml","pt",1);
    }
}
