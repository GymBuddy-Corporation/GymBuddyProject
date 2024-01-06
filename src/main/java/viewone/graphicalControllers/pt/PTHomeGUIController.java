package viewone.graphicalControllers.pt;

import exceptions.dataException.DataFieldException;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import viewone.manageListView.ManageGymCommunicationList;
import utils.MainStage;
import utils.SwitchPage;
import javafx.fxml.FXML;
import viewone.manageListView.listCells.GymCommunicationListCellFactory;

import java.io.IOException;
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
        try {
            //todo con db
            ManageGymCommunicationList.setCommunicationList(communicationList);
        } catch (DataFieldException e) {
            try {
                e.callMe(1);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        communicationList.setCellFactory(new GymCommunicationListCellFactory());
        //ManageGymCommunicationList.setListenerCommunication(communicationList, this);
    }
}
