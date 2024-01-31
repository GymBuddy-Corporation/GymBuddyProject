package viewone.managelistview;


import beans.GymInfoBean;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ListView;
import viewone.managelistview.interfaces.GymListGUIInterface;
import java.util.List;

public class ManageGymList {

    private ManageGymList() {}

    public static void setListnere(ListView<GymInfoBean> gymList, GymListGUIInterface interfaceToGUI) {
        gymList.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends GymInfoBean> observableValue, GymInfoBean oldItem, GymInfoBean newItem) {
                        if(newItem==null)return;
                        interfaceToGUI.setInfo(newItem);
                    }
                });
    }

    public static void setGymList(ListView<GymInfoBean> gymList, List<GymInfoBean> listOfBeans)  {
        gymList.getItems().addAll(listOfBeans);
    }
}