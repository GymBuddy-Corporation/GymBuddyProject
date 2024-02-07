package viewtwo.popups.controllers;

import beans.CouponsBean;
import engineering.popups.PopupBaseClass;
import engineering.popups.PopupBaseController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import utils.listview.ManageGenericList;
import utils.listview.SetInfoListViewInterface;
import viewtwo.managelistview.listcells.SearchCouponsCellFactory;
import viewtwo.popups.SearchCouponPopUp;

import java.util.List;

public class SearchCouponPopUpController extends PopupBaseController implements SetInfoListViewInterface {
    SearchCouponPopUp caller;

    @FXML
    ListView<Object> couponsListView;
    @Override
    public void setCaller(PopupBaseClass caller) {
            this.caller= (SearchCouponPopUp) caller;
    }

    public void setBean(List<CouponsBean> beans) {
        ManageGenericList.setList(couponsListView,beans);
        couponsListView.setCellFactory(new SearchCouponsCellFactory());
        ManageGenericList.setListnere(couponsListView,this );
    }

    @Override
    public void setInfo(Object bean) {
            caller.selectedCoupon((CouponsBean) bean);
    }
}
