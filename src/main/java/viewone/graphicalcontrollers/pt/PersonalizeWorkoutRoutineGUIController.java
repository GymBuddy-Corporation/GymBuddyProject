package viewone.graphicalcontrollers.pt;

import beans.EmailBean;
import beans.RequestBean;
import beans.WorkoutRoutineBean;
import controllers.SatisfyWorkoutRequestsController;
import controllers.UserAccessController;
import engineering.LoggedTrainerSingleton;
import exceptions.DBUnrreachableException;
import exceptions.EmailFormException;
import exceptions.NoLoggedUserException;
import exceptions.SubmitRoutineException;
import exceptions.logger.CostumeLogger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import utils.SwitchPage;
import viewone.popup.PopupAbstract;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class PersonalizeWorkoutRoutineGUIController extends PopupAbstract implements Initializable {
    WorkoutRoutineBean workoutRoutine;
    RequestBean requestBean;

    @FXML TextArea commentTextArea;
    @FXML TextArea nameRoutineTextArea;
    @FXML private Text usernameText;

    public void logout() {
            UserAccessController controller = new UserAccessController();
            controller.logout();
            SwitchPage.changePage( "Login.fxml", "launcher", 1);

    }
    @FXML
    public void submitRoutine() {
        try {
            popUpCreate("Vuoi confermare la creazione?","Submit","Back");
        } catch (IOException e) {
            CostumeLogger.getInstance().logError(e);
        }
    }
    @FXML
    public void goBack(){
        SwitchPage.changePage("CreateNewWorkoutRoutine.fxml.fxml","pt",1);
    }
    public void setValue(RequestBean request, WorkoutRoutineBean workoutRoutine) {
        this.requestBean = request;
        this.workoutRoutine = workoutRoutine;
        commentTextArea.setPromptText("Insert here your comment for "+ requestBean.getAthleteBean().getUsername() +"s' workout routine.");
        nameRoutineTextArea.setPromptText("Insert here the Workout Routines' name");
    }

    @Override
    public void popUpConfirm()  {
        SatisfyWorkoutRequestsController controller;
        try{
            controller = new SatisfyWorkoutRequestsController();
            this.workoutRoutine.setComment(commentTextArea.getText());
            this.workoutRoutine.setName(nameRoutineTextArea.getText());
        } catch (NoLoggedUserException | SubmitRoutineException e){
            e.callMe(1);
            return;
        }
        try {
                controller.sendWorkoutRoutine(requestBean, this.workoutRoutine);
                EmailBean email = new EmailBean(LoggedTrainerSingleton.getSingleton().getMyBean(), requestBean.getAthleteBean());
                email.setBody("NAME WORKOUT ROUTINE:\n" + nameRoutineTextArea.getText() +
                        "\n\nCOMMENT WORKOUT ROUTINE:\n" + commentTextArea.getText());
                controller.sendEmailWithoutObject(email);
        } catch (DBUnrreachableException | EmailFormException e) {
                e.callMe(1);
        } catch (URISyntaxException | IOException e){
                CostumeLogger.getInstance().logError(e);
        }
        SwitchPage.changePage("PTHome.fxml","pt",1);
    }

    @Override
    public void popUpDelete() {
        commentTextArea.clear();
        nameRoutineTextArea.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameText.setText(new UserAccessController().getUser().getUsername());
    }
}