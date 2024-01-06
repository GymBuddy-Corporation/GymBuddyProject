package viewone.graphicalControllers.athlete;

import beans.AthleteBean;
import beans.RequestBean;
import beans.UserBean;
import controllers.CreateRequestController;
import engineering.LoggedUserSingleton;
import exceptions.dataException.DataFieldException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Athlete;
import utils.MainStage;
import utils.SwitchPage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AskNewRoutineGUIController implements Initializable {
    private RequestBean requestBean;
    @FXML
    private TextArea infoCommentTextArea;

    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"AthletesRoutine.fxml","athlete",1);
    }
    public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"AthleteLogin.fxml","launcher",1);
    }

    @FXML
    public void askRequest() throws Exception{
        CreateRequestController createRequestController = new CreateRequestController();
        try {

            //todo if sta text area = null allora non andare avanti
            AthleteBean athleteBean= (AthleteBean) LoggedUserSingleton.getMyBean();
            this.requestBean = new RequestBean(infoCommentTextArea.getText(), athleteBean, athleteBean.getTrainerFC());
            createRequestController.askForNewWorkoutRoutine(this.requestBean);
            SwitchPage.setStage(MainStage.getStage(),"AthleteHome.fxml","launcher",1);
        } catch (DataFieldException e) {
            try {
                e.callMe(1);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        //TODO gestisci la rischiesta di una nuova scheda
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //imposta la scheda associata a lui
    }
}
