package viewone.graphicalControllers.gym;

import controllers.UserAccessController;
import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

public class ProfileGymGUIController {

    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"GymHome.fxml","gym",1);
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
        SwitchPage.setStage(MainStage.getStage(),"GymFirst.fxml","launcher",1);
    }
    @FXML
    public void editProfile() throws Exception {
        //TODO gestisci la gestione dell'account
        SwitchPage.setStage(MainStage.getStage(),"ProfileGym.fxml","gym",1);
    }
}
