package viewone.graphicalControllers.athlete;

import controllers.UserAccessController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import utils.MainStage;
import utils.SwitchPage;


public class ViewOldRoutinesGUIController {

    @FXML
    private ListView<String> workoutsList;

    @FXML
    public void goBack() throws Exception {
        UserAccessController controller=new UserAccessController();
        controller.logout();
        SwitchPage.setStage(MainStage.getStage(),"Login.fxml","launcher",1);
    }
    public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"AthleteLogin.fxml","launcher",1);
    }
    @FXML
    public void viewOldRoutine() {
        //TODO gestisci la visualizzazione di una vecchia scheda
    }

}