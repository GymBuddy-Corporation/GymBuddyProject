package viewtwo.graphicalcontrollers.pt;

import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
import exceptions.NoLoggedUserException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import utils.MainStage;
import utils.SwitchPage;
import viewone.graphicalControllers.pt.EmailSystemGUIController;
import viewtwo.engegnering.MainMenuSingleton;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PTSingleRequestGUIController2 {
    @FXML private Label requestLabel;
    @FXML private Label nameAthleteLabel;
    private RequestBean requestBean;
    public void setStuff(RequestBean requestBean){
        this.requestBean = requestBean;
        requestLabel.setText(requestBean.getInfo());
        nameAthleteLabel.setText(requestBean.getAthleteBean().getUsername() + "'s request");
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
        SatisfyWorkoutRequestsController controller;
        try{
            controller = new SatisfyWorkoutRequestsController();
        } catch (NoLoggedUserException e){
            try {
                e.callMe(1);
                return;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        controller.rejectRequest(requestBean);
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
        EmailSystemGUIController2 controller = (EmailSystemGUIController2) MainMenuSingleton.getMainMenu().setActivity("EmailSystem2.fxml","pt");
        Objects.requireNonNull(controller).setValue(requestBean);
        //todo da fare grafica MainMenuSingleton.getMainMenu().setActivity("ManageCommunication.fxml", "pt");
    }
}
