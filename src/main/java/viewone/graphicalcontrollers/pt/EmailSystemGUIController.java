package viewone.graphicalcontrollers.pt;

import beans.EmailBean;
import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
import controllers.UserAccessController;
import engineering.LoggedUserSingleton;
import exceptions.EmailFormException;
import exceptions.NoLoggedUserException;
import exceptions.logger.CostumeLogger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import utils.MainStage;
import utils.SwitchPage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmailSystemGUIController implements Initializable {
    @FXML private TextArea objectTextArea;
    @FXML private TextArea contentTextArea;
    @FXML private Text usernameText;
    RequestBean selectedRequest;
    @FXML public void sendEmail() throws IOException{
        try{
            EmailBean emailBean = new EmailBean(LoggedUserSingleton.getSingleton().getMyBean(), selectedRequest.getAthleteBean());
            String object = objectTextArea.getText();
            String content = contentTextArea.getText();
            emailBean.setObject(object);
            emailBean.setBody(content);
            SatisfyWorkoutRequestsController controller = new SatisfyWorkoutRequestsController();
            controller.sendEmailWithObject(emailBean);
        } catch (NoLoggedUserException | EmailFormException e){
            e.callMe(1);
            return;
        } catch (URISyntaxException e) {
            CostumeLogger.getInstance().logError(e);
        }
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }

    @FXML public void logout() {
        try {
            UserAccessController controller = new UserAccessController();
            controller.logout();
            SwitchPage.setStage(MainStage.getStage(), "Login.fxml", "launcher", 1);
        } catch (IOException e){
            CostumeLogger.getInstance().logError(e);
        }
    }

    public void setValue(RequestBean selectedRequest) {
        this.selectedRequest = selectedRequest;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameText.setText(new UserAccessController().getUser().getUsername());
    }
}
