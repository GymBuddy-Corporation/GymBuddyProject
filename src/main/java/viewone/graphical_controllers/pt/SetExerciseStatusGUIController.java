package viewone.graphical_controllers.pt;

import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

public class SetExerciseStatusGUIController {

    @FXML
    public void logout() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"PTLogin.fxml","launcher",1);
    }
    @FXML
    public void turnBackToRoutine() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"SatisfyWorkoutRoutineRequest.fxml","pt",1);
    }
    @FXML
    public void searchExercise(){
        //TODO gestisci la ricerca di un esercizio dal database
    }
    @FXML
    public void changeStatus(){
        //TODO gestisci il cambio dello stato dell'esercizio
    }



}
