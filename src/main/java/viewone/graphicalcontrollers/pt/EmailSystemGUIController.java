package viewone.graphicalcontrollers.pt;

import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
import engineering.LoggedUserSingleton;
import exceptions.EmailFormException;
import exceptions.NoLoggedUserException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import utils.MainStage;
import utils.SwitchPage;

import java.io.IOException;
import java.net.URISyntaxException;

public class EmailSystemGUIController {
    @FXML private TextArea objectTextArea;
    @FXML private TextArea contentTextArea;
    RequestBean selectedRequest;
    @FXML public void sendEmail() throws IOException{
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
        try {
            controller.sendClarificationEmail(LoggedUserSingleton.getSingleton().getMyBean(), selectedRequest.getAthleteBean(), objectTextArea.getText(), contentTextArea.getText());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (EmailFormException e){
            e.callMe(1);
            return;
        }
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }

    @FXML public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"Login.fxml","launcher",1);
    }

    public void setValue(RequestBean selectedRequest) {
        this.selectedRequest = selectedRequest;
    }
}
