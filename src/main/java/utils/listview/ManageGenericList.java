package utils.listview;

import exceptions.logger.CostumeLogger;
import javafx.scene.control.ListView;

import java.util.List;

public  class ManageGenericList {

    private ManageGenericList() {}

    public static void setListnere(ListView<Object> listView, SetInfoListViewInterface interfaceToGUI) {
        listView.getSelectionModel().selectedItemProperty().
                addListener((observableValue, oldItem, newItem) -> {
                    if(newItem==null)return;
                    interfaceToGUI.setInfo(newItem);
                });
    }
    public static void setListnere(ListView<Object> listView, SetInfoListViewInterface interfaceToGUI,boolean repeatable) {
        listView.getSelectionModel().selectedItemProperty().
                addListener((observableValue, oldItem, newItem) -> {
                    if(newItem==null){
                        CostumeLogger.getInstance().logString("ddd");
                        if(!repeatable)return;
                        interfaceToGUI.setInfo(oldItem);
                        return;
                    }

                    interfaceToGUI.setInfo(newItem);
                });
    }

        public static void setList(ListView<Object> listview, List<?> items)  {
        listview.getItems().clear();
        listview.getItems().addAll(items);
    }
}