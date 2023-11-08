package viewone.graphical_controllers.athlete;

import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

public class AthletesHomeGUIController {

    //TODO manage the exceptions
    @FXML
    public void manageAthletes() {
        String path = "/viewone/gym/GymUserView.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }

    @FXML
    public void manageWorkoutRoutine() {
        String path = "/viewone/athlete/AthleteRoutine.fxml";
        SwitchPage.setStage(MainStage.getStage(), path);
    }

    public void manageWorkouts() {
        String path = "/viewone/athlete/AthleteWorkouts.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }

    public void logout() {
        String path = "/viewone/launcher/GymFirst.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }
}
