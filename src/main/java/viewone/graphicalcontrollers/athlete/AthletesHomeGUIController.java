package viewone.graphicalcontrollers.athlete;

import beans.ActiveMembershipBean;
import controllers.ManageMembershipController;
import controllers.UserAccessController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import utils.MainStage;
import utils.SwitchPage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AthletesHomeGUIController implements Initializable {
    private final String athlete = "athlete";
    @FXML
    Text userName;
    @FXML
    Text gymName;
    @FXML
    Text expirationDate;
    @FXML
    Text points;
    @FXML
    public void manageWorkoutRoutine() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"AthleteRoutine.fxml",athlete,1);
    }

    @FXML
        public void viewPTCommunications() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"AthletePTCommunications.fxml",athlete,1);
    }

    @FXML
    public void manageWorkouts() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"AthleteWorkouts.fxml",athlete,1);
    }

    @FXML
    public void logout() throws Exception{
        UserAccessController controller=new UserAccessController();
        controller.logout();
        SwitchPage.setStage(MainStage.getStage(),"Login.fxml","launcher",1);
    }

    @FXML
    public void membership() throws IOException {
        SwitchPage.setStage(MainStage.getStage(),"ManageMembershipSearchGym.fxml",athlete,1);
    }
    @FXML
    public void openProfile() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"ProfileAthlete.fxml",athlete,1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            UserAccessController accessController=new UserAccessController();
            userName.setText(accessController.getUser().getUsername());
            ActiveMembershipBean bean=ManageMembershipController.getActiveMembership();
            gymName.setText(bean.getGymName());
            points.setText(String.valueOf(bean.getPoints()));
            String end=bean.getEndOfMembership()!=null?bean.getEndOfMembership().toString():"";
            expirationDate.setText(end);
    }
}
