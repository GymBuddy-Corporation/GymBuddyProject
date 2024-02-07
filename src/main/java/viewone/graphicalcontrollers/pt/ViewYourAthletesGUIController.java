package viewone.graphicalcontrollers.pt;

import beans.AthleteBean;
import controllers.SatisfyWorkoutRequestsController;
import controllers.UserAccessController;
import javafx.scene.text.Text;
import viewone.managelistview.listcells.AthleteListCellFactory;
import viewone.managelistview.ManageAthletesList;
import exceptions.dataexception.DataFieldException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import utils.MainStage;
import utils.SwitchPage;


import java.net.URL;
import java.util.*;

public class ViewYourAthletesGUIController implements Initializable{

    @FXML private ListView<AthleteBean> athletesList;
    @FXML private Label labelName;
    @FXML private Label labelSurname;
    @FXML private Label labelUserame;
    @FXML private Label labelBirthDate;
    @FXML private Label labelEmail;
    @FXML private Label labelFiscalCode;
    @FXML private Text usernameText;

    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }
    public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"Login.fxml","launcher",1);
    }

    public void setInfoBox(AthleteBean selectedSubscriber) {
        labelName.setText(" Name: " + selectedSubscriber.getPersonalInfo().getName());
        labelSurname.setText(" Surname: " + selectedSubscriber.getPersonalInfo().getSurname());
        labelUserame.setText(" Username: " + selectedSubscriber.getUsername());
        labelBirthDate.setText(" Birth: " + selectedSubscriber.getPersonalInfo().getDateOfBirth());
        labelEmail.setText(" Email: " + selectedSubscriber.getCredentials().getEmail());
        labelFiscalCode.setText(" FiscalCode: " + selectedSubscriber.getPersonalInfo().getFc());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ManageAthletesList.setAthletesList(athletesList);
        } catch (DataFieldException e) {

                e.callMe(1);
                return;

        }
        usernameText.setText(new UserAccessController().getUser().getUsername());
        athletesList.setCellFactory(new AthleteListCellFactory());
        ManageAthletesList.setListenerAthletes(athletesList, this);
    }
}
