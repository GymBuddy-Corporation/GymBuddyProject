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
    public void goBack() throws Exception {
        String path = "/viewone/pt/PTHome.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }
    @FXML
    public void logout() throws Exception {
        String path = "/viewone/launcher/PTLogin.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }
    @FXML
    public void editExercise() throws Exception {
        //TODO gestisci la modifica di un esercizio già esistente
        String path = "/viewone/pt/PTHome.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }
    @FXML
    public void goHome() throws Exception {
        String path = "/viewone/pt/PTHome.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }
    public void addNewExercise() throws Exception {
        //TODO gestisci l'aggiunta di un esercizio
        String path = "/viewone/pt/AddExercise.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }
    @FXML
    public void cancelExercise() throws Exception {
        //TODO gestisci la cancellazione di un esercizio
        String path = "/viewone/pt/PTHome.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }
}
