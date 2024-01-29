package viewone.graphicalControllers.athlete;

import controllers.UserAccessController;
import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

import java.io.IOException;

public class AthletesHomeGUIController {
    private final String athlete = "athlete";

    @FXML
    public void manageWorkoutRoutine() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"AthleteRoutine.fxml",athlete,1);
    }

    @FXML
        public void viewPTCommunications() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"AthletePTCommunications.fxml",athlete,1);
    }

    @FXML
    public void manageWorkouts() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"AthleteWorkouts.fxml",athlete,1);
    }

    @FXML
    public void logout() throws Exception{
        UserAccessController controller=new UserAccessController();
        controller.logout();
        SwitchPage.setStage(MainStage.getStage(),"Login.fxml","launcher",1);
    }

    @FXML
    public void membership() throws IOException {
        SwitchPage.setStage(MainStage.getStage(),"ManageMembershipSearchGym.fxml",athlete,1);
    }
    @FXML
    public void openProfile() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"ProfileAthlete.fxml",athlete,1);
    }
}
