package viewone.graphical_controllers.athletes;

import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

public class AthletesHomeGUIController {
    @FXML
    public void manageAthletes() throws Exception {
        String path = "/viewone/gym/GymUserView.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }

    @FXML
    public void managePT() throws Exception {
        String path = "/viewone/gym/GymPTView.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }

    public void manageGymEquipment() throws Exception {
        String path = "/viewone/gym/GymEquipmentView.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }

    public void logout() throws Exception {
        String path = "/viewone/launcher/GymFirst.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }
}
