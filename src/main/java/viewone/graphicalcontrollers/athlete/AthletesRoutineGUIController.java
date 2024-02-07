package viewone.graphicalcontrollers.athlete;


import beans.AthleteBean;
import beans.ExerciseForWorkoutRoutineBean;
import beans.WorkoutRoutineBean;
import controllers.CreateRequestController;
import controllers.UserAccessController;
import engineering.LoggedUserSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import utils.MainStage;
import utils.SwitchPage;

import java.net.URL;
import java.util.ResourceBundle;


public class AthletesRoutineGUIController implements Initializable {

    WorkoutRoutineBean exerciseBeanList;
    @FXML private ListView<ExerciseForWorkoutRoutineBean> routineExerciselist;
    @FXML private Button mondayButton;

    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"AthleteHome.fxml","athlete",1);
    }
    public void logout() throws Exception {
        UserAccessController controller=new UserAccessController();
        controller.logout();
        SwitchPage.setStage(MainStage.getStage(),"Login.fxml","launcher",1);
    }
    @FXML
    public void askForNewRoutine() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"AskNewRoutine.fxml","athlete",1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mondayButton.fire();
        CreateRequestController controller = new CreateRequestController();
        controller.loadWorkoutRoutine((AthleteBean) LoggedUserSingleton.getSingleton().getMyBean());

        exerciseBeanList = controller.loadWorkoutRoutine((AthleteBean) LoggedUserSingleton.getSingleton().getMyBean());

        //imposta la scheda associata a lui (???)
    }

    public void dayButtonAction() {
        //stub method
    }
}