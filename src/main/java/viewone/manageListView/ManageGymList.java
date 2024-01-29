package viewone.manageListView;

import beans.AthleteBean;
import beans.CredentialsBean;
import beans.GymInfoBean;
import beans.PersonalInfoBean;
import exceptions.dataException.DataFieldException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ListView;
import viewone.graphicalControllers.pt.ViewYourAthletesGUIController;
import viewone.manageListView.interfaces.GymListGUIInterface;

import java.time.LocalDate;
import java.util.List;

public class ManageGymList {

    private ManageGymList() {}

    public static void setListnere(ListView<GymInfoBean> gymList, GymListGUIInterface interfaceToGUI) {
        gymList.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends GymInfoBean> observableValue, GymInfoBean oldItem, GymInfoBean newItem) {
                        interfaceToGUI.setInfo(newItem);
                    }
                });
    }

    public static void setGymList(ListView<GymInfoBean> gymList, List<GymInfoBean> listOfBeans)  {
        gymList.getItems().addAll(listOfBeans);
    }
}