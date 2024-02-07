package viewtwo.graphicalcontrollers.home;

import beans.ActiveMembershipBean;
import beans.AthleteBean;
import beans.PersonBean;
import beans.UserBean;
import controllers.ManageMembershipController;
import controllers.UserAccessController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonInfoGUIController implements Initializable {
    @FXML
    private Label email;

    @FXML
    private AnchorPane membershipCorner;

    @FXML
    private Label membershipDate;

    @FXML
    private Label membershipName;

    @FXML
    private Label membershipPoints;

    @FXML
    private Label name;

    @FXML
    private Label surname;

    @FXML
    private Label username;

    @FXML
    private Label membershipLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserAccessController userAccessController=new UserAccessController();
        UserBean userBean=userAccessController.getUser();
        if(userBean instanceof AthleteBean){
            currentMembershipInizializer();
        }
        if(userBean instanceof PersonBean perosn){
            personaInformationInizializer(perosn);
        }
    }

    private void currentMembershipInizializer() {
        membershipCorner.setVisible(true);
        membershipLabel.setVisible(true);

        ActiveMembershipBean bean=ManageMembershipController.getActiveMembership();
        membershipName.setText(bean.getGymName());
        membershipDate.setText(bean.getEndOfMembership().toString());
        membershipPoints.setText(bean.getPoints()+" points");
    }

    private void personaInformationInizializer(PersonBean bean){
        name.setText(bean.getPersonalInfo().getName());
        surname.setText(bean.getPersonalInfo().getSurname());
        username.setText(bean.getUsername());
        email.setText(bean.getCredentials().getEmail());
    }
}
