package viewone.graphicalcontrollers.pt;

import controllers.UserAccessController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import utils.SwitchPage;
import viewone.managelistview.ManageGymCommunicationList;
import viewone.managelistview.listcells.GymCommunicationListCellFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class PTHomeGUIController implements Initializable{

    private static final String PT="PT";
    private  static final int VIEW=1;
    private static final String LAUNCHER="launcher";


    @FXML private Text usernameText;
    @FXML private ListView<String> communicationList;
    @FXML
    public void sendCommunication() {
        SwitchPage.changePage("ManageCommunication.fxml",PT,VIEW);

    }
    @FXML
    public void yourAthletes() {
        SwitchPage.changePage("ViewYourAthletes.fxml",PT,VIEW);
    }
    @FXML
    public void logout()  {
        UserAccessController controller=new UserAccessController();
        controller.logout();
        SwitchPage.changePage("Login.fxml", LAUNCHER, VIEW);
    }

    @FXML
    public void viewRequests() {
        SwitchPage.changePage("ViewWorkoutRoutineRequests.fxml", PT, VIEW);
    }

    @FXML
    public void manageCourse() {
        SwitchPage.changePage("ManageCourse.fxml", PT, VIEW);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ManageGymCommunicationList.setCommunicationList(communicationList);
        communicationList.setCellFactory(new GymCommunicationListCellFactory());
    }
}
