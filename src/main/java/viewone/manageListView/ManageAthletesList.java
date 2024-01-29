package viewone.manageListView;

import beans.AthleteBean;
import beans.CredentialsBean;
import beans.PersonalInfoBean;
import exceptions.dataException.DataFieldException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ListView;
import viewone.graphicalControllers.pt.ViewYourAthletesGUIController;
import java.time.LocalDate;

public class ManageAthletesList {

    private ManageAthletesList() {}

    public static void setListenerAthletes(ListView<AthleteBean> athletesList, ViewYourAthletesGUIController viewYourAthletesGUIController) {
        athletesList.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends AthleteBean> observableValue, AthleteBean oldItem, AthleteBean newItem) {
                        listEventAthletes(newItem, viewYourAthletesGUIController);
                    }
                });
    }

    private static void listEventAthletes(AthleteBean newItem,  ViewYourAthletesGUIController viewYourAthletesGUIController) {
        viewYourAthletesGUIController.setInfoBox(newItem);
    }

    public static void setAthletesList(ListView<AthleteBean> athletesList) throws DataFieldException {
        PersonalInfoBean pi1 = new PersonalInfoBean("Luca", "Martorelli", LocalDate.of(2000, 9, 1), "MRTLCU00P01D612J", 'm');
        PersonalInfoBean pi2 = new PersonalInfoBean("Mario", "Regine", LocalDate.of(2002, 9, 23), "MHTLCU00P01D612J", 'm');
        PersonalInfoBean pi3 = new PersonalInfoBean("Edoardo", "Manenti", LocalDate.of(2007, 9, 6), "NRTLCU00P01D612J", 'm');
        PersonalInfoBean pi4 = new PersonalInfoBean("Alexandru", "Nazare", LocalDate.of(2002, 5, 15), "MDBJEU00P01D612J", 'm');
        PersonalInfoBean pi5 = new PersonalInfoBean("Alessandro", "Cortese", LocalDate.of(1999, 6, 1), "MFCUCU00P01D612J", 'm');


        AthleteBean athlete1 = new AthleteBean("LuX71", pi1,
                CredentialsBean.ctorWithSyntaxCheck("lucam0109@gmail.com", "SSLAZIO1900"));
        AthleteBean athlete2 = new AthleteBean("accroccoman", pi2,
                CredentialsBean.ctorWithSyntaxCheck("accroccoman@gmail.com", "megliololiodiItri"));
        AthleteBean athlete4 = new AthleteBean("EdoMan00", pi3,
                CredentialsBean.ctorWithSyntaxCheck("edoman00@gmail.com", "cyber"));
        AthleteBean athlete3 = new AthleteBean("AlexNazi", pi4,
                CredentialsBean.ctorWithSyntaxCheck("gigachad2002@gmail.com", "gigachad"));

        athletesList.getItems().addAll(athlete1, athlete2, athlete3, athlete4);
    }
}
