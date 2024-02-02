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
import javafx.scene.control.TextArea;
import utils.MainStage;
import utils.SwitchPage;
import viewone.popup.PopupAbstract;

import java.io.IOException;
import java.net.URISyntaxException;

public class PersonalizeWorkoutRoutineGUIController extends PopupAbstract {
    WorkoutRoutineBean workoutRoutine;
    RequestBean requestBean;

    @FXML TextArea commentTextArea;
    @FXML TextArea nameRoutineTextArea;

    public void logout() {
        try {
            UserAccessController controller = new UserAccessController();
            controller.logout();
            SwitchPage.setStage(MainStage.getStage(), "Login.fxml", "launcher", 1);
        } catch (IOException e){
            CostumeLogger.getInstance().logError(e);
        }
    }
    @FXML
    public void submitRoutine() throws Exception{
            popUpCreate("Vuoi confermare la creazione?","Submit","Back");
    }
    @FXML
    public void goBack() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"CreateNewWorkoutRoutine.fxml.fxml","pt",1);
    }
    public void setValue(RequestBean request, WorkoutRoutineBean workoutRoutine) {
        this.requestBean = request;
        this.workoutRoutine = workoutRoutine;
        commentTextArea.setPromptText("Insert here your comment for "+ requestBean.getAthleteBean().getUsername() +"s' workout routine.");
        nameRoutineTextArea.setPromptText("Insert here the Workout Routines' name");
    }

    @Override
    public void popUpConfirm() throws IOException {
        SatisfyWorkoutRequestsController controller;
        try{
            controller = new SatisfyWorkoutRequestsController();
            this.workoutRoutine.setComment(commentTextArea.getText());
            this.workoutRoutine.setName(nameRoutineTextArea.getText());
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
        } catch (NoLoggedUserException | SubmitRoutineException e){
                e.callMe(1);
        }
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }

    @Override
    public void popUpDelete() {
        commentTextArea.clear();
        nameRoutineTextArea.clear();
    }
}