package viewone.graphicalControllers.pt;

import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

public class ConfirmSubmitRoutineGUIController {

    private RequestBean requestBean;
    private SatisfyWorkoutRequestsController satisfyWorkoutRequestsController;

    @FXML
    public void goBack() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"AddCommentToWorkoutRoutine.fxml","pt",1);
    }
    @FXML
    public void submitRoutine() throws Exception{
        //TODO gestisci la vera creazione di una scheda
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }



}
