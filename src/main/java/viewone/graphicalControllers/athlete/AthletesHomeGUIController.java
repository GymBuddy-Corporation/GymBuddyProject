package viewone.graphicalControllers.athlete;

import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

public class AthletesHomeGUIController {

    @FXML
    public void manageWorkoutRoutine() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"AthleteRoutine.fxml","athlete",1);
    }

    @FXML
        public void viewPTCommunications() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"AthletePTCommunications.fxml","athlete",1);
    }

    @FXML
    public void manageWorkouts() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"AthleteWorkouts.fxml","athlete",1);
    }

    @FXML
    public void logout() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"AthleteLogin.fxml","launcher",1);
    }

    @FXML
    public void openProfile() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"ProfileAthlete.fxml","athlete",1);
    }
}
