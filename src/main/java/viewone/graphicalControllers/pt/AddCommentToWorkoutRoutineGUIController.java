package viewone.graphicalControllers.pt;

import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import utils.MainStage;
import utils.SwitchPage;
import viewone.popup.PopupAbstract;
import viewone.popup.PopupGymbuddy;

import java.io.IOException;

public class AddCommentToWorkoutRoutineGUIController extends PopupAbstract {

    SatisfyWorkoutRequestsController satisfyWorkoutRequestsController;
    RequestBean requestBean;

    @FXML TextArea commentTextArea;
    @FXML TextArea nameRoutineTextArea;

    public void logout(MouseEvent event) {
        // Your logout logic here
    }
    @FXML
    public void submitRoutine() throws Exception{
            popUpCreate("Vuoi confermare la creazione?","Submit","Back");
            //implementa il successivo invio definitivo della scheda, notifica e cambio pagina, segnando
            //la richiesta come completata
    }
    @FXML
    public void goBack() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"SatisfyWorkoutRoutineRequest.fxml.fxml","pt",1);
    }
    public void setValue(RequestBean request, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) {
        this.requestBean = request;
        this.satisfyWorkoutRequestsController = satisfyWorkoutRequestsController;
        commentTextArea.setPromptText("Insert here your comment for "+ requestBean.getAthleteBean().getUsername() +"s' workout routine.");
        nameRoutineTextArea.setPromptText("Insert here the Workout Routines' name");
    }

    @Override
    public void popUpConfirm() throws IOException {
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }

    @Override
    public void popUpDelete() {
        //comment to avoid code smell
    }
}