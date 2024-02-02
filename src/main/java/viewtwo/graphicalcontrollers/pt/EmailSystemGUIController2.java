package viewtwo.graphicalcontrollers.pt;

import beans.EmailBean;
import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
import engineering.LoggedUserSingleton;
import exceptions.EmailFormException;
import exceptions.NoLoggedUserException;
import exceptions.logger.CostumeLogger;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import viewtwo.engegnering.MainMenuSingleton;

import java.io.IOException;
import java.net.URISyntaxException;

public class EmailSystemGUIController2 {

    @FXML private TextField objectTextField;
    @FXML private TextArea contentTextField;

    RequestBean selectedRequest;

    @FXML
    public void sendEmail() throws IOException {
        try{
            EmailBean emailBean = new EmailBean(LoggedUserSingleton.getSingleton().getMyBean(),
                    selectedRequest.getAthleteBean());
            String object = objectTextField.getText();
            String content = contentTextField.getText();
            emailBean.setObject(object);
            emailBean.setBody(content);
            SatisfyWorkoutRequestsController controller = new SatisfyWorkoutRequestsController();
            controller.sendEmailWithObject(emailBean);
        } catch (NoLoggedUserException | EmailFormException e){
            e.callMe(2);
            return;
        } catch(URISyntaxException | IOException e2){
            CostumeLogger.getInstance().logError(e2);
        }
        MainMenuSingleton.getMainMenu().setActivity("ptHome.fxml", "pt");
    }

    public void setValue(RequestBean selectedRequest) {
        this.selectedRequest = selectedRequest;
    }

}
