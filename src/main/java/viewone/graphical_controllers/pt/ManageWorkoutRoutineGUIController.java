package viewone.graphical_controllers.pt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import utils.MainStage;
import utils.SwitchPage;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ManageWorkoutRoutineGUIController {

    @FXML private RadioButton fridayRadioButton;
    @FXML private RadioButton mondayRadioButton;
    @FXML private RadioButton saturdayRadioButton;
    @FXML private TextField searchExerciseText;

    @FXML private RadioButton sundayRadioButton;
    @FXML private RadioButton thursdayRadioButton;
    @FXML private RadioButton tuesdayRadioButton;
    @FXML private RadioButton wednesdayRadioButton;

    private Button previousButton;

    private final List<String> buttonNameList = new ArrayList<>(Arrays.asList(
            "mondayButton",
            "tuesdayButton",
            "wednesdayButton",
            "thursdayButton",
            "fridayButton",
            "saturdayButton",
            "sundayButton"
    ));

    private void colorShift(Button button){
        if(previousButton!=null){
            previousButton.setStyle(null);
        }
        //TODO sistema i colori
        button.setStyle("-fx-background-color: linear-gradient(to bottom, #00e4af, #5d7cf3);" +
                "-fx-background-radius:  5em;" + "-fx-border-color:  #2D043D;" +
                "-fx-border-radius:  5em;" + "-fx-text-fill: #2D043D;");
        previousButton = button;
    }

    public String dayButtonAction(ActionEvent event) {
        String sourceId = ((Node) event.getSource()).getId();
        colorShift((Button) event.getSource());
        for(int i = 0; i < 7; i++){
            if(Objects.equals(sourceId, buttonNameList.get(i))) {
                return DayOfWeek.of(i+1).name();
            }
        }
        return null;
    }
    @FXML
    public void logout() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"PTLogin.fxml","launcher",1);
    }
    @FXML
    public void deleteChanges() throws Exception{
        //TODO gestisci la cancellazione della scheda
        SwitchPage.setStage(MainStage.getStage(),"OpenSingleRequest.fxml","pt",1);
    }
    @FXML
    public void goHome() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }
    public void submitRoutine() throws Exception{
        //TODO gestisci il submit di una nuova scheda
        /*SwitchPage.setStage(MainStage.getStage(),"AddExercise.fxml","pt",1);*/
    }
    public void addExercise(){
        //TODO gestisci l'aggiunta di un esercizio nella scheda
    }
    @FXML
    public void searchExercise(){
        //TODO gestisci la ricerca di un esercizio dal database
    }
}
