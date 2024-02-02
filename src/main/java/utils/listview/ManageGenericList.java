package utils.listview;

import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public  class ManageGenericList {

    private ManageGenericList() {}

    public static void setListnere(ListView<Object> gymList, SetInfoListViewInterface interfaceToGUI) {
        gymList.getSelectionModel().selectedItemProperty().
                addListener((observableValue, oldItem, newItem) -> {
                    if(newItem==null)return;
                    interfaceToGUI.setInfo(newItem);
                });
    }

        public static void setList(ListView<Object> listview, List<?> items)  {
        listview.getItems().addAll(items);
    }
}