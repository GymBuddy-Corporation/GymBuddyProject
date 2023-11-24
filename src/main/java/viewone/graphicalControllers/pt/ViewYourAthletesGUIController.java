package viewone.graphicalControllers.pt;

import beans.AthleteBean;
import beans.CredentialsBean;
import beans.PersonalInfoBean;
import engineering.AthleteListCellFactory;
import engineering.ManageAthletesList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import utils.MainStage;
import utils.SwitchPage;


import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class ViewYourAthletesGUIController implements Initializable{

    @FXML
    /*private ListView<EventBean> dashboard;

    private final ArrayList<EventBean> elements= new ArrayList<>();*/
    private ListView<AthleteBean> athletesList;
    @FXML private Label labelName;
    @FXML private Label labelSurname;
    @FXML private Label labelUserame;
    @FXML private Label labelBirthDate;
    @FXML private Label labelEmail;
    @FXML private Label labelFiscalCode;

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

    public void setInfoBox(AthleteBean selectedSubscriber) {
        labelName.setText(" Name: " + selectedSubscriber.getPersonalInfo().getName());
        labelSurname.setText(" Surname: " + selectedSubscriber.getPersonalInfo().getSurname());
        labelUserame.setText(" Username: " + selectedSubscriber.getUsernameBean());
        labelBirthDate.setText(" Birth: " + selectedSubscriber.getPersonalInfo().getDateOfBirth());
        labelEmail.setText(" Email: " + selectedSubscriber.getCredentials().getEmail());
        labelFiscalCode.setText(" FiscalCode: " + selectedSubscriber.getPersonalInfo().getFc());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PersonalInfoBean pi1 = new PersonalInfoBean("Luca", "Martorelli", LocalDate.of(2000, 9, 1), "MRTLCU00P01D612J", 'm');
        PersonalInfoBean pi2 = new PersonalInfoBean("Mario", "Regine", LocalDate.of(2002, 9, 23), "MRTLCU00P01D612J", 'm');
        PersonalInfoBean pi3 = new PersonalInfoBean("Edoardo", "Manenti", LocalDate.of(2007, 9, 6), "MRTLCU00P01D612J", 'm');
        PersonalInfoBean pi4 = new PersonalInfoBean("Alexandru", "Nazare", LocalDate.of(2002, 5, 15), "MRTLCU00P01D612J", 'm');
        PersonalInfoBean pi5 = new PersonalInfoBean("Alessandro", "Cortese", LocalDate.of(1999, 6, 1), "MRTLCU00P01D612J", 'm');


        AthleteBean athlete1 = new AthleteBean("LuX71", pi1,
                CredentialsBean.ctorWithSyntaxCheck("lucam0109@gmail.com", "SSLAZIO1900"));
        AthleteBean athlete2 = new AthleteBean("accroccoman", pi2,
                CredentialsBean.ctorWithSyntaxCheck("accroccoman@gmail.com", "megliololiodiItri"));
        AthleteBean athlete31 = new AthleteBean("EdoMan00", pi3,
                CredentialsBean.ctorWithSyntaxCheck("edoman00@gmail.com", "cyber"));
        AthleteBean athlete3 = new AthleteBean("AlexNazi", pi4, CredentialsBean.ctorWithSyntaxCheck("gigachad2002@gmail.com", "gigachad"));
        /*AthleteBean athlete4 = new AthleteBean("Xavel");
        AthleteBean athlete5 = new AthleteBean("EdmondDantes");
        AthleteBean athlete6 = new AthleteBean("ALessandroLori");
        AthleteBean athlete7 = new AthleteBean("RonnieColeman");
        AthleteBean athlete8 = new AthleteBean("Cbum");
        AthleteBean athlete9 = new AthleteBean("Andreaxdf");
        AthleteBean athlete10 = new AthleteBean("EdoMan00");
        AthleteBean athlete11 = new AthleteBean("Cortix");*/
        athletesList.getItems().addAll(athlete1, athlete2, athlete3/*, athlete4, athlete5, athlete6,
                athlete7, athlete8, athlete9, athlete10, athlete11*/, athlete31);
        athletesList.setCellFactory(new AthleteListCellFactory());
        ManageAthletesList.setListenerAthletes(athletesList, /*metti il controller applicativo, */this);
    }
}
