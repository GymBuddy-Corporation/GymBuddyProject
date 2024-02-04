package viewone.graphicalcontrollers.pt;

import controllers.UserAccessController;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import viewone.managelistview.ManageGymCommunicationList;
import utils.MainStage;
import utils.SwitchPage;
import javafx.fxml.FXML;
import viewone.managelistview.listcells.GymCommunicationListCellFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class PTHomeGUIController implements Initializable{

    @FXML private Text usernameText;
    @FXML private ListView<String> communicationList;
    @FXML
    public void sendCommunication() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"ManageCommunication.fxml","pt",1);
        //TODO caso d'uso
        //Objects.requireNonNull(controller).setValue(loggedTrainer, sendCommunicationController);
    }
    @FXML
    public void yourAthletes() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"ViewYourAthletes.fxml","pt",1);
    }
    @FXML
    public void logout() throws Exception {
        UserAccessController controller=new UserAccessController();
        controller.logout();
        SwitchPage.setStage(MainStage.getStage(), "Login.fxml", "launcher", 1);
    }

    @FXML
    public void viewRequests() throws Exception{
        SwitchPage.setStage(MainStage.getStage(), "ViewWorkoutRoutineRequests.fxml", "pt", 1);
    }

    @FXML
    public void manageCourse() throws Exception{
        SwitchPage.setStage(MainStage.getStage(), "ManageCourse.fxml", "pt", 1);
    }

    public void setValue() {
        //TODO poi quando avremo un DB possiamo toglierla
        // e finire il tutto
        /*this.loggedTrainer = trainer;
        usernameText.setText(loggedTrainer.getUsername());*/
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //todo con db
        ManageGymCommunicationList.setCommunicationList(communicationList);
        communicationList.setCellFactory(new GymCommunicationListCellFactory());
        //ManageGymCommunicationList.setListenerCommunication(communicationList, this);
    }
}
