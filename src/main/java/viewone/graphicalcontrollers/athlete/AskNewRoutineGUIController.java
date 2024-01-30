package viewone.graphicalcontrollers.athlete;

import beans.AthleteBean;
import beans.RequestBean;
import beans.WorkoutRoutineBean;
import controllers.CreateRequestController;
import controllers.UserAccessController;
import engineering.LoggedAthleteSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import utils.MainStage;
import utils.SwitchPage;
import java.net.URL;
import java.util.ResourceBundle;

public class AskNewRoutineGUIController implements Initializable {
    @FXML
    private TextArea infoCommentTextArea;
    private WorkoutRoutineBean workoutRoutineBean;

    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"AthletesRoutine.fxml","athlete",1);
    }
    public void logout() throws Exception {
        UserAccessController controller=new UserAccessController();
        controller.logout();
        SwitchPage.setStage(MainStage.getStage(),"Login.fxml","launcher",1);
    }

    @FXML
    public void askRequest() throws Exception{
        CreateRequestController createRequestController = new CreateRequestController();
        if(infoCommentTextArea.getText()==null){
                return;
        }
        AthleteBean athleteBean= LoggedAthleteSingleton.getSingleton().getMyBean();
        RequestBean requestBean = new RequestBean(infoCommentTextArea.getText(), athleteBean, athleteBean.getTrainerFC());
        createRequestController.askForNewWorkoutRoutine(requestBean);
        SwitchPage.setStage(MainStage.getStage(),"AthleteHome.fxml","athlete",1);


        //TODO gestisci la rischiesta di una nuova scheda
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //this.workoutRoutineBean = ;
    //imposta la scheda associata a lui
    }
}
