package viewone.graphical_controllers.athlete;

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
    public void manageWorkoutRoutine() throws Exception {
        String path = "/viewone/athlete/AthleteRoutine.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }

    public void manageWorkouts() throws Exception {
        String path = "/viewone/athlete/AthleteWorkouts.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }

    public void logout() throws Exception {
        String path = "/viewone/launcher/GymFirst.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }
}
