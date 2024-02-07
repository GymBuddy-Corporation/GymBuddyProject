package viewtwo.popups.controllers;

import beans.GymInfoBean;
import engineering.popups.PopupBaseClass;
import engineering.popups.PopupBaseController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import utils.listview.ManageGenericList;
import utils.listview.SetInfoListViewInterface;
import viewtwo.managelistview.listcells.SearchGymCellFactory;
import viewtwo.popups.SearchGymPopUp;

import java.util.List;

public class SearchGymPopUpController extends PopupBaseController implements SetInfoListViewInterface {
    SearchGymPopUp caller;
    List<GymInfoBean> beans;

    @FXML
    ListView<Object> gymsListView;
    public void setBean(List<GymInfoBean> beans){
        this.beans=beans;
        ManageGenericList.setList(gymsListView,beans);
        gymsListView.setCellFactory(new SearchGymCellFactory());
        ManageGenericList.setListnere(gymsListView,this);

    }

    @Override
    public void setCaller(PopupBaseClass caller) {
            this.caller= (SearchGymPopUp) caller;
    }

    @Override
    public void setInfo(Object bean) {
            caller.selectedGym((GymInfoBean) bean);
    }
}
