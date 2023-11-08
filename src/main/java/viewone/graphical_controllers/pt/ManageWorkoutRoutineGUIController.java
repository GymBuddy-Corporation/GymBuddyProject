package viewone.graphical_controllers.pt;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import utils.MainStage;
import utils.SwitchPage;

public class ManageWorkoutRoutineGUIController {

    @FXML private RadioButton fridayRadioButton;
    @FXML private RadioButton mondayRadioButton;
    @FXML private RadioButton saturdayRadioButton;
    @FXML private TextField searchExerciseText;

    @FXML private RadioButton sundayRadioButton;
    @FXML private RadioButton thursdayRadioButton;
    @FXML private RadioButton tuesdayRadioButton;
    @FXML private RadioButton wednesdayRadioButton;

    @FXML
    public void logout() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"PTLogin.fxml","launcher",1);
    }
    @FXML
    public void deleteChanges() throws Exception{
        //TODO gestisci la cancellazione dei cambiamenti
        SwitchPage.setStage(MainStage.getStage(),"OpenSingleRequest.fxml","pt",1);
    }
    @FXML
    public void goHome() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }
    public void submitRoutine() throws Exception{
        //TODO gestisci il submit di una nuova scheda
        String path = "/viewone/pt/AddExercise.fxml";
        SwitchPage.setStage(MainStage.getStage(),"OpenSingleRequest.fxml","pt",1);
    }
    @FXML
    public void searchExercise(){
        //TODO gestisci la ricerca di un esercizio nel database
    }
}
