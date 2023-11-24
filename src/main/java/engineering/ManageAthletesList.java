package engineering;

import beans.AthleteBean;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import viewone.graphical_controllers.pt.ViewYourAthletesGUIController;

import java.util.List;

public class ManageAthletesList {

    private ManageAthletesList() {}

    public static void setListenerAthletes(ListView<AthleteBean> athletesList,/*TODO metti poi un controller applicativo, forse*/ ViewYourAthletesGUIController viewYourAthletesGUIController) {
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

    //forse sto update è inutile
    public static void updateList(ListView<AthleteBean> athleteBeanListView, List<AthleteBean> athleteBeanList) {
        ObservableList<AthleteBean> athleteBeanObservableList = FXCollections.observableList(athleteBeanList);
        athleteBeanListView.setItems(athleteBeanObservableList);
    }

}