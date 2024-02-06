package viewtwo.popups.controllers;

import beans.MembershipBean;
import javafx.fxml.FXML;
import engineering.popups.PopupBaseClass;
import engineering.popups.PopupBaseController;
import utils.listview.ManageGenericList;
import utils.listview.SetInfoListViewInterface;
import viewtwo.managelistview.listCells.SearchMembershipCellFactory;
import viewtwo.popups.SearchMembershipPopUp;
import javafx.scene.control.ListView;
import java.util.List;

public class SearchMembershipPopUpController extends PopupBaseController implements SetInfoListViewInterface {

    SearchMembershipPopUp caller;
    @FXML
    private ListView<Object> membershipsListView;
    @Override
    public void setCaller(PopupBaseClass caller) {
        this.caller= (SearchMembershipPopUp) caller;
    }
    List<MembershipBean> beans;
    public void setBean(List<MembershipBean> beans) {
        this.beans=beans;
        ManageGenericList.setList(membershipsListView,beans);
        membershipsListView.setCellFactory(new SearchMembershipCellFactory());
        ManageGenericList.setListnere(membershipsListView,this);
    }

    @Override
    public void setInfo(Object bean) {
        caller.selectMembership((MembershipBean) bean);
    }
}
