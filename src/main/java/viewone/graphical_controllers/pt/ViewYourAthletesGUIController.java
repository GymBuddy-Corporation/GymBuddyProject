package viewone.graphical_controllers.pt;

import beans.AthleteBean;
import beans.UserBean;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import utils.MainStage;
import utils.SwitchPage;


import java.net.URL;
import java.util.*;

public class ViewYourAthletesGUIController implements Initializable{

    @FXML
    /*private ListView<EventBean> dashboard;

    private final ArrayList<EventBean> elements= new ArrayList<>();*/
    private ListView<AthleteBean> athletesList;

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
        SwitchPage.setStage(MainStage.getStage(),"PTLogin.fxml","launcher",1);
    }

    private void setInfoBox(UserBean selectedSubscriber) {
        /*if(!clicked) {
            infoSubscriberBox.setDisable(false);
            infoSubscriberBox.setVisible(true);
            clicked = true;
        }
        infoName.setText(" Name: " + selectedSubscriber.getName());
        infoSurname.setText(" Surname: " + selectedSubscriber.getSurname());
        infoUsername.setText(" Username: " + selectedSubscriber.getUsername());
        infoBirth.setText(" Birth: " + selectedSubscriber.getBirth());
        infoEmail.setText(" Email: " + selectedSubscriber.getEmail());
        infoFiscalCode.setText(" FiscalCode: " + selectedSubscriber.getFiscalCode());*/
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        AthleteBean athlete1 = new AthleteBean("LuX71");
        AthleteBean athlete2 = new AthleteBean("accroccoman");
        AthleteBean athlete3 = new AthleteBean("AlexNazi");
        AthleteBean athlete4 = new AthleteBean("Xavel");
        AthleteBean athlete5 = new AthleteBean("EdmondDantes");
        AthleteBean athlete6 = new AthleteBean("ALessandroLori");
        AthleteBean athlete7 = new AthleteBean("RonnieColeman");
        AthleteBean athlete8 = new AthleteBean("Cbum");
        AthleteBean athlete9 = new AthleteBean("Andreaxdf");
        AthleteBean athlete10 = new AthleteBean("EdoMan00");
        AthleteBean athlete11 = new AthleteBean("Cortix");
        athletesList.getItems().addAll(athlete1, athlete2, athlete3, athlete4, athlete5, athlete6,
                athlete7, athlete8, athlete9, athlete10, athlete11);
    }
}
