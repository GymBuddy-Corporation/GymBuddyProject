package viewone.graphicalcontrollers.athlete;

import beans.GymInfoBean;
import beans.MembershipBean;
import controllers.ManageMembershipController;
import controllers.UserAccessController;
import exceptions.NoLoggedUserException;
import exceptions.NoUserFoundException;
import exceptions.logger.CostumeLogger;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import utils.MainStage;
import utils.SwitchPage;
import utils.listview.ManageGenericList;
import utils.listview.SetInfoListViewInterface;
import viewone.managelistview.listcells.MembershipListCellFactory;
import viewone.popup.membershippopups.MembershipConfirmPopupInterface;
import viewone.popup.membershippopups.MembershipPopUP;


import java.io.IOException;
import java.util.List;

public class ManageMembershipCreateMembershipGUIController  implements SetInfoListViewInterface, MembershipConfirmPopupInterface {
    @FXML
    ListView<Object> membershipListView;

    ManageMembershipController controller;

    public void goBack()  {
        try {
            SwitchPage.setStage(MainStage.getStage(),"AthleteHome.fxml","athlete",1);
        } catch (IOException e) {
            CostumeLogger.getInstance().logError(e);
        }
    }
    public void logout() throws NoLoggedUserException {
        UserAccessController controllerAccess=new UserAccessController();
        controllerAccess.logout();
        try {
            SwitchPage.setStage(MainStage.getStage(),"Login.fxml","launcher",1);
        } catch (IOException e) {
            CostumeLogger.getInstance().logError(e);
        }
    }

    public void initialize(GymInfoBean bean) throws NoLoggedUserException, NoUserFoundException {
        controller=new ManageMembershipController();
        List<MembershipBean> membershipBeanList= controller.getMembershipList(bean);
        ManageGenericList.setList(membershipListView,membershipBeanList);
        membershipListView.setCellFactory(new MembershipListCellFactory());
        ManageGenericList.setListnere(membershipListView,this);
    }

    @Override
    public void setInfo(Object bean) {
        try {
            MembershipPopUP.getLoginPopup(this,(MembershipBean) bean,"MembershipConfirmBean.fxml","athlete",1);
        } catch (IOException e) {
            CostumeLogger.getInstance().logError(e);
        }
    }

    @Override
    public void conferma(MembershipBean bean) {
        System.out.println(bean.getName());
    }
}
