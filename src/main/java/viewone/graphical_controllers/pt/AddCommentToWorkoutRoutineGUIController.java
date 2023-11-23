package viewone.graphical_controllers.pt;

import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import utils.MainStage;
import utils.SwitchPage;
import viewone.engineering.Popup_abstract;
import viewone.engineering.Popup_gymbuddy;

import java.util.Objects;

public class AddCommentToWorkoutRoutineGUIController extends Popup_abstract {

    SatisfyWorkoutRequestsController satisfyWorkoutRequestsController;
    RequestBean requestBean;

    @FXML TextArea commentTextArea;

    public void logout(MouseEvent event) {
        // Your logout logic here
    }
    @FXML
    public void submitRoutine() throws Exception{
        Popup_gymbuddy.startPopUp(this,"ciao","come","va");
    }
    @FXML
    public void goBack() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"SatisfyWorkoutRoutineRequest.fxml.fxml","pt",1);
    }
    public void setValue(RequestBean request, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) {
        this.requestBean = request;
        this.satisfyWorkoutRequestsController = satisfyWorkoutRequestsController;
        commentTextArea.setPromptText("Insert here your comment for "+ requestBean.getAthleteBean().getUsernameBean() +"s' workout routine.");
    }

    @Override
    public void popUpConfirm() {

    }

    @Override
    public void popUpDelete() {

    }
}
