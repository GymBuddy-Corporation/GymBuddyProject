package viewone.graphicalControllers.pt;

import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
import engineering.LoggedUserSingleton;
import exceptions.dataException.DataFieldException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import utils.MainStage;
import utils.SwitchPage;

import java.io.IOException;
import java.net.URISyntaxException;

public class EmailSystemGUIController {
    @FXML private TextArea objectTextArea;
    @FXML private TextArea contentTextArea;
    @FXML private Button sendButton;
    RequestBean selectedRequest;
    @FXML public void sendEmail() throws IOException{
        SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
        try {
            satisfyWorkoutRequestsController.sendClarificationEmail(LoggedUserSingleton.getSingleton().getMyBean(), selectedRequest.getAthleteBean(), objectTextArea.getText(), contentTextArea.getText());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        //TODO Alex controlla se l'eccezione è gestita bene e BASTA QUESTO PER LE EMAIL
        // O BISOGNA FARE ALTRO
        //TODO gestisci infine la scrittura su db e il passaggio della richiesta in toClarify
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }

    @FXML public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"Login.fxml","launcher",1);
    }

    public void setValue(RequestBean selectedRequest) {
        this.selectedRequest = selectedRequest;
    }
}
