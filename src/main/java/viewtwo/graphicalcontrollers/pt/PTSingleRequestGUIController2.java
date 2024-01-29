package viewtwo.graphicalcontrollers.pt;

import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import viewtwo.engegnering.MainMenuSingleton;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PTSingleRequestGUIController2 implements Initializable {
    @FXML private Label requestLabel;
    @FXML private Label nameAthleteLabel;
    @FXML private Button buttonSatisfyRequest;
    @FXML private Button buttonAskClarification;
    @FXML private Button buttonRejectRequest;
    private RequestBean requestBean;
    public void setStuff(RequestBean requestBean){
        this.requestBean = requestBean;
        requestLabel.setText(requestBean.getInfo());
        nameAthleteLabel.setText(requestBean.getAthleteBean().getUsername());
    }
    @FXML
    public void satisfyRequest() throws Exception {
        CreateNewWorkoutRoutineGUIController2 controller = (CreateNewWorkoutRoutineGUIController2) MainMenuSingleton.getMainMenu().setActivity("CreateNewWorkoutRoutine2.fxml", "pt");
        Objects.requireNonNull(controller).setValue(requestBean);
    }
    @FXML
    public void goBack() throws Exception {
        MainMenuSingleton.getMainMenu().setActivity("ptHome.fxml", "pt");
    }
    @FXML
    public void rejectRequest() throws Exception {
        SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
        //satisfyWorkoutRequestsController.rejectRequest(selectedRequest);
        MainMenuSingleton.getMainMenu().setActivity("ptHome.fxml", "pt");
        /*try{

            //fai la cancellazione della richiesta sul db e cambia page
        } catch (DataFieldException e) {
            try {
                e.callMe(1);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }*/


    }

    @FXML
    public void askClarification() throws Exception {
        //EmailSystemGUIController controller = (EmailSystemGUIController) SwitchPage.setStage(MainStage.getStage(),"EmailSystem.fxml","pt",1);
        //Objects.requireNonNull(controller).setValue(selectedRequest);
        //todo da fare grafica MainMenuSingleton.getMainMenu().setActivity("ManageCommunication.fxml", "pt");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
    }
}
