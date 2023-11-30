package viewone.graphicalControllers.pt;

import beans.AthleteBean;
import controllers.SatisfyWorkoutRequestsController;
import engineering.manageListView.listCells.AthleteListCellFactory;
import engineering.manageListView.ManageAthletesList;
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
    private SatisfyWorkoutRequestsController satisfyWorkoutRequestsController;

    /*private void clear(){
        elements.clear();
    }
    private void refresh(){
        dashboard.refresh();
    }*/

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
        ManageAthletesList.setAthletesList(athletesList, satisfyWorkoutRequestsController);

        athletesList.setCellFactory(new AthleteListCellFactory());
        ManageAthletesList.setListenerAthletes(athletesList, /*metti il controller applicativo, */this);
    }
}
