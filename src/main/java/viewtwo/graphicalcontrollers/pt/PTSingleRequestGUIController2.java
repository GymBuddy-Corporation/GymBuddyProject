package viewtwo.graphicalcontrollers.pt;

import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
import exceptions.NoLoggedUserException;
import exceptions.logger.CostumeLogger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import viewtwo.engegnering.MainMenuSingleton;

import java.io.IOException;
import java.util.Objects;

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
    public void satisfyRequest() {
        try{
            CreateNewWorkoutRoutineGUIController2 controller = (CreateNewWorkoutRoutineGUIController2) MainMenuSingleton.getMainMenu().setActivity("CreateNewWorkoutRoutine2.fxml", "pt");
            Objects.requireNonNull(controller).setValue(requestBean);
        } catch (IOException e){
            CostumeLogger.getInstance().logError(e);
        }
    }
    @FXML
    public void goBack() {
        try{
            MainMenuSingleton.getMainMenu().setActivity("ptHome.fxml", "pt");
        } catch (IOException e){
            CostumeLogger.getInstance().logError(e);
        }
    }
    @FXML
    public void rejectRequest() {
        try{
            SatisfyWorkoutRequestsController controller = new SatisfyWorkoutRequestsController();
            controller.rejectRequest(requestBean);
            MainMenuSingleton.getMainMenu().setActivity("ptHome.fxml", "pt");
        } catch (NoLoggedUserException e){
                e.callMe(2);
        }catch (IOException e) {
            CostumeLogger.getInstance().logError(e);
        }
    }

    @FXML public void askClarification(){
        try{
            EmailSystemGUIController2 controller = (EmailSystemGUIController2) MainMenuSingleton.getMainMenu().setActivity("EmailSystem2.fxml","pt");
            Objects.requireNonNull(controller).setValue(requestBean);
        } catch (IOException e) {
            CostumeLogger.getInstance().logError(e);
        }
    }
}
