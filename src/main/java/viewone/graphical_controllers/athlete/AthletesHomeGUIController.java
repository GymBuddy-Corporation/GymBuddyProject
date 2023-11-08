package viewone.graphical_controllers.athlete;

import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

public class AthletesHomeGUIController {

    @FXML
    public void manageWorkoutRoutine() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"AthleteRoutine.fxml","athlete",1);
    }

/*    @FXML
        public void viewPTCommunications throws Exception() {
        SwitchPage.setStage(MainStage.getStage(),"AthleteRoutine.fxml","athlete",1);
    }*/

    public void manageWorkouts() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"AthleteWorkouts.fxml","athlete",1);
    }

    public void logout() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"GymFirst.fxml","launcher",1);
    }
}
