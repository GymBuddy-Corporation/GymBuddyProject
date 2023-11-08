package viewone.graphical_controllers.pt;

import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

public class SingleRequestGUIController {


    //TODO manage the difference between the eidt and the creation of a workout routine
    @FXML
    public void goBack() throws Exception {
        //TODO metti il bottone per andare indietro
        SwitchPage.setStage(MainStage.getStage(),"ViewWorkoutRoutineRequests.fxml","pt",1);
    }
    @FXML
    public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"PTLogin.fxml","launcher",1);
    }
    @FXML
    public void editRoutine() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"ManageWorkoutRoutine.fxml","pt",1);
    }
    @FXML
    public void goHome() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }
    public void createNewRoutine() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"ManageWorkoutRoutine.fxml","pt",1);
    }
    @FXML
    public void cancelRequest() throws Exception {
        //TODO gestisci la cancellazione di una richiesta
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }
}
