package viewone.graphical_controllers.pt;

import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

public class ProfilePTGUIController {

    @FXML
    public void goBack() throws Exception {
        String path = "/viewone/pt/PTHome.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }
    @FXML
    public void logout() throws Exception {
        String path = "/viewone/launcher/PTLogin.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }
    @FXML
    public void deleteAccount() throws Exception {
        //TODO gestisci l'eliminazione dell'account con notifiche e pop-up
        String path = "/viewone/launcher/PTLogin.fxml";
        SwitchPage.setStage(MainStage.stage, path);
    }
    @FXML
    public void goHome() throws Exception {
        String path = "/viewone/pt/PTHome.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }
    @FXML
    public void editProfile() throws Exception {
        String path = "/viewone/pt/ProfilePT.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }
}
