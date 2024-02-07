package viewone.graphicalcontrollers.athlete;

import beans.CardInfoBean;
import beans.CouponsBean;
import beans.GymInfoBean;
import beans.MembershipBean;
import controllers.ManageMembershipController;
import controllers.UserAccessController;
import exceptions.*;
import exceptions.dataexception.DataFieldException;
import exceptions.logger.CostumeLogger;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import utils.MainStage;
import utils.SwitchPage;
import utils.listview.ManageGenericList;
import utils.listview.NoIntractableModel;
import utils.listview.SetInfoListViewInterface;
import viewone.managelistview.listcells.MembershipListCellFactory;
import viewone.managelistview.listcells.SelectedCouponsListCellFactory;
import viewone.popup.membershippopups.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManageMembershipCreateMembershipGUIController  implements SetInfoListViewInterface, MembershipConfirmPopupInterface, CouponConfirmationInterface, BuyPopUpInterface {
    public static final String ATHLETE = "athlete";
    public static final String LAUNCHER = "launcher";
    public static final int VIEW =1;

    @FXML
    ListView<Object> membershipListView;
    @FXML
    ListView<Object> couponListView;

    @FXML
    ListView<Object> selectedCuponsListView;
    ManageMembershipController controller;
    MembershipBean selectedMembership;
    GymInfoBean selectedGym;
    List<CouponsBean> selectedCoupons;
    @FXML
    Text selectedMembershipNameLabel;
    @FXML
    Text priceLabel;
    @FXML
    Text durationLabel;
    @FXML
    Text pointsLabel;
    @FXML
    Text userName;
    List<CouponsBean> loadedCupons;
    MembershipBean composedMembership;

    public void logout()  {
        UserAccessController controllerAccess=new UserAccessController();
        controllerAccess.logout();
        try {
            SwitchPage.setStage(MainStage.getStage(),"Login.fxml",LAUNCHER,VIEW);
        } catch (IOException e) {
            CostumeLogger.getInstance().logError(e);
        }
    }

    public void initialize(GymInfoBean bean) throws NoLoggedUserException, NoUserFoundException, DBUnrreachableException {
        controller=new ManageMembershipController();
        selectedGym=bean;
        List<MembershipBean> membershipBeanList= controller.getMembershipList(bean);
        loadedCupons=controller.getCouponsList(bean);
        ManageGenericList.setList(membershipListView,membershipBeanList);
        membershipListView.setCellFactory(new MembershipListCellFactory());
        ManageGenericList.setListnere(membershipListView,this);
        ManageGenericList.setList(couponListView,loadedCupons);
        couponListView.setCellFactory(new SelectedCouponsListCellFactory());
        ManageGenericList.setListnere(couponListView,this,true);
        selectedCoupons=new ArrayList<>();
        ManageGenericList.setList(selectedCuponsListView,selectedCoupons);
        selectedCuponsListView.setCellFactory(new SelectedCouponsListCellFactory());
        selectedCuponsListView.setSelectionModel(new NoIntractableModel<>());
        userName.setText(new UserAccessController().getUser().getUsername());

    }

    @Override
    public void setInfo(Object bean) {
        try {
            if(bean instanceof CouponsBean temp) CouponConfirmationPopup.getPopup(this,temp,"CouponConfirmPopup.fxml",ATHLETE,VIEW);
            if(bean instanceof MembershipBean temp)MembershipPopUP.getLoginPopup(this,temp,"MembershipConfirmPopup.fxml",ATHLETE,VIEW);
        } catch (IOException e) {
            CostumeLogger.getInstance().logError(e);
        }
    }

    private  void loadComposedMembership(){
        if(selectedMembership==null)return;
        try {
            composedMembership=controller.applyCouponsToMembership(selectedGym,selectedMembership,selectedCoupons);
        } catch (MembershipCouponNotFoundException |DBUnrreachableException| CouponNotCumulativeException e) {
            e.callMe(VIEW);
           cancel();
            return;
        } catch (DecoratorNoBaseComponentException e) {
            CostumeLogger.getInstance().logError(e);
            return;
        } catch (NoUserFoundException e) {
            try {
                SwitchPage.setStage(MainStage.getStage(),"ManageMembershipSearchGym.fxml",ATHLETE,VIEW);
                return;
            } catch (IOException ex) {
                CostumeLogger.getInstance().logError(ex);
                return;
            }
        }
        priceLabel.setText(composedMembership.getPrice()+"$");
        durationLabel.setText(composedMembership.getDurationInDays()+"days");
        pointsLabel.setText(composedMembership.getPointsAwardedOnBuy()+"points");
    }

    @Override
    public void confermaPopupMembership(MembershipBean bean) {
        if(bean!=null)selectedMembership=bean;
        selectedMembershipNameLabel.setText(selectedMembership.getName());
        loadComposedMembership();
    }

    @Override
    public void confermaCuoponPopup(CouponsBean bean) {
        if(bean!=null)selectedCoupons.add(bean);
        ManageGenericList.setList(selectedCuponsListView,selectedCoupons);
        if(selectedMembership!=null)loadComposedMembership();
        couponListView.getItems().clear();
        ManageGenericList.setList(couponListView,loadedCupons);

    }

    public void cancel(){
        selectedCoupons.clear();
        selectedCuponsListView.getItems().clear();
        ManageGenericList.setList(selectedCuponsListView,selectedCoupons);
        loadComposedMembership();
    }

    public void buy() throws IOException {
            BuyPopUp.getPayPopUp(this,"BuyPopUp.fxml",ATHLETE,VIEW);
    }

    @Override
    public void buyMembership(CardInfoBean bean, boolean remember) {
        try {
            controller.payNewMebership(selectedGym, selectedMembership, selectedCoupons, bean, remember);
        }catch (DBUnrreachableException | PaymentFailedException | MembershipOnlyForNewUserException |
                CouponNotCumulativeException | FailedToSaveNewMembership | DecoratorNoBaseComponentException |
                MembershipCouponNotFoundException | DataFieldException e ) {
            e.callMe(VIEW);
           return;
        } catch (NoUserFoundException e) {
            goBack();
        }
        goToHome();
    }

    public void goBack()  {
        try {
            SwitchPage.setStage(MainStage.getStage(),"ManageMembershipSearchGym.fxml",ATHLETE,VIEW);
        } catch (IOException e) {
            CostumeLogger.getInstance().logError(e);
        }
    }

    public void goToHome(){
        try {
            SwitchPage.setStage(MainStage.getStage(),"AthleteHome.fxml", ATHLETE,VIEW);
        } catch (IOException e) {
            CostumeLogger.getInstance().logError(e);
        }
    }

    @Override
    public void buyMembership() {
        try {
            controller.payNewMembership(selectedGym, selectedMembership, selectedCoupons);
        }catch (DBUnrreachableException | MembershipOnlyForNewUserException |
                CouponNotCumulativeException | FailedToSaveNewMembership | DecoratorNoBaseComponentException |
                MembershipCouponNotFoundException e) {
            e.callMe(VIEW);
            return;
        } catch (NoUserFoundException e) {
            goBack();
        } catch (CostumException e) {
            e.callMe(VIEW);
            return;
        }
        goToHome();
    }
}
