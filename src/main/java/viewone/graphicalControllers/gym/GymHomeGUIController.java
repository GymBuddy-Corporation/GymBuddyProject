package viewone.graphicalControllers.gym;

import controllers.UserAccessController;
import utils.MainStage;
import utils.SwitchPage;
import javafx.fxml.FXML;

public class GymHomeGUIController {

    @FXML
    public void manageAthletes() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"GymUserView.fxml","gym",1);
    }

    @FXML
    public void managePT() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"GymPTView.fxml","gym",1);
    }
    @FXML
    public void manageGymEquipment() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"GymEquipmentView.fxml","gym",1);
    }
    @FXML
    public void sendCommunication() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"SendGymCommunication.fxml","gym",1);
    }
    @FXML
    public void logout() throws Exception {
        UserAccessController controller=new UserAccessController();
        controller.logout(1);
        SwitchPage.setStage(MainStage.getStage(),"Login.fxml","launcher",1);
    }

    @FXML
    public void seeProfile() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"ProfileGym.fxml","gym",1);
    }

}
