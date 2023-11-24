package viewone.graphical_controllers.pt;

import beans.ExerciseBean;
import beans.ExerciseForWorkoutRoutineBean;
import beans.RequestBean;
import beans.SearchBean;
import controllers.SatisfyWorkoutRequestsController;
import engineering.ExerciseListCellFactory;
import engineering.ManageExerciseList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import utils.MainStage;
import utils.SwitchPage;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class SetExerciseStatusGUIController implements Initializable{

    private Map<String, List<ExerciseForWorkoutRoutineBean>> dayExercisesMap;
    private RequestBean requestBean;
    private SatisfyWorkoutRequestsController satisfyWorkoutRequestsController;
    @FXML private ListView<ExerciseBean> exerciseDBList;
    @FXML private Button suspendStatusButton;
    @FXML private Button activeStatusButton;
    @FXML private TextField searchExerciseText;

    @FXML
    public void logout() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"PTLogin.fxml","launcher",1);
    }
    @FXML
    public void turnBackToRoutine() throws Exception{
        SatisfyWorkoutRoutineRequestGUIController controller = (SatisfyWorkoutRoutineRequestGUIController) SwitchPage.setStage(MainStage.getStage(),"SatisfyWorkoutRoutineRequest.fxml","pt",1);
        Objects.requireNonNull(controller).setBackValue(requestBean, satisfyWorkoutRequestsController, dayExercisesMap);
    }
    @FXML
    public void searchExercise(){
        //TODO controlla se funziona
        List<ExerciseBean> exerciseBeanList = satisfyWorkoutRequestsController.searchExercise(new SearchBean(searchExerciseText.getText()));
        System.out.println("Exercise Bean List Size: " + exerciseBeanList.size());
        ManageExerciseList.updateList(exerciseDBList, exerciseBeanList);
    }
    @FXML
    public void changeStatus(){
        //TODO gestisci il cambio dello stato dell'esercizio
    }

    public void setVisibleButtons(Boolean bool) {
        activeStatusButton.setVisible(bool);
        suspendStatusButton.setVisible(bool);
    }

    public void setValue(RequestBean requestBean, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, Map<String, List<ExerciseForWorkoutRoutineBean>> dayExercisesMap) {
        this.dayExercisesMap=dayExercisesMap;
        this.requestBean= requestBean;
        this.satisfyWorkoutRequestsController=satisfyWorkoutRequestsController;
        ManageExerciseList.setListenerDBSet(exerciseDBList, satisfyWorkoutRequestsController, this);
        List<ExerciseBean> exerciseBeanList = satisfyWorkoutRequestsController.getGymExercises();
        ManageExerciseList.updateList(exerciseDBList, exerciseBeanList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exerciseDBList.setCellFactory(new ExerciseListCellFactory());
        setVisibleButtons(false);
    }



}
