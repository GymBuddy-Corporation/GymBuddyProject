package viewtwo.graphicalcontrollers.athlete;


import beans.*;
import controllers.ManageMembershipController;
import exceptions.*;
import exceptions.dataexception.DataFieldException;
import exceptions.logger.CostumeLogger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import utils.listview.ManageGenericList;
import viewtwo.engegnering.MainMenuSingleton;
import viewtwo.managelistview.listcells.SelectedCouponCellFactory;
import viewtwo.popups.BuyPopUp;
import viewtwo.popups.SearchCouponPopUp;
import viewtwo.popups.SearchGymPopUp;
import viewtwo.popups.SearchMembershipPopUp;
import viewtwo.popups.abstracts.BuyPopupInterface;
import viewtwo.popups.abstracts.SearchCouponInterface;
import viewtwo.popups.abstracts.SearchGymInterface;
import viewtwo.popups.abstracts.SearchMembershipInterface;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ManageMembershipAthleteGUIController implements Initializable , SearchGymInterface , SearchMembershipInterface , SearchCouponInterface , BuyPopupInterface {


    public static final String POPUPS = "popups";
    private static final int VIEW=2;
    GymInfoBean selectedGym;
    MembershipBean selectedMembership;
    List<CouponsBean> selectedCoupons;
    @FXML
    private AnchorPane couponsCorner;
    @FXML
    private ListView<Object> couponsListview;
    @FXML
    private TextArea descriptionMembership;
    @FXML
    private Label durationFinal;
    @FXML
    private Label durationMembership;
    @FXML
    private Label gymAddress;
    @FXML
    private Label gymCity;
    @FXML
    private AnchorPane gymCorner;
    @FXML
    private Label gymCountry;
    @FXML
    private Label gymName;
    @FXML
    private AnchorPane membershipCorner;
    @FXML
    private Label nameMembership;
    @FXML
    private AnchorPane payCorner;
    @FXML
    private Label pointsFinal;
    @FXML
    private Label priceFinal;
    @FXML
    private Label priceMembership;
    @FXML
    private AnchorPane resetCorner;
    @FXML
    private Label rewardMembership;
    @FXML
    private Button searchCouponButton;
    @FXML
    private Button resetMembership;
    @FXML
    private Button resetCoupons;
    @FXML
    private TextField searchGymTextField;
    @FXML
    private Button searchMembershipButton;
    @FXML
    private Button payButton;
    @FXML
    private Label selectedCouponsLabel;
    @FXML
    private Label selectedMembershipLabel;
    private ManageMembershipController manageMembershipController;

    @FXML
    void pay(MouseEvent event) throws IOException {
        BuyPopUp.getSearchPopUp(this,manageMembershipController.fetchSavedCardStub(),"PayPopUp.fxml", POPUPS,VIEW);
    }

    @FXML
    void searchButton(MouseEvent event) {
        String filter = searchGymTextField.getText();
        SearchGymBean searchBean = new SearchGymBean(filter, filter, filter, filter);
        List<GymInfoBean> resultOfSearch;
        try {
            resultOfSearch = manageMembershipController.searchGym(searchBean,false);
        } catch (DBUnrreachableException e) {
            e.callMe(VIEW);
            return;
        }
        try {
            SearchGymPopUp.getSearchPopUp(this, resultOfSearch,"SearchGymPopUp.fxml",POPUPS,VIEW);
        } catch (IOException e) {
            CostumeLogger.getInstance().logError(e);
        }
    }

    @Override
    public void searchResult(GymInfoBean bean) {
        if(bean==null || Objects.equals(bean.getName(), "") || bean.getName()==null)return;
        gymName.setText(bean.getName());
        gymAddress.setText(bean.getAddress());
        gymCity.setText(bean.getCity());
        gymCountry.setText(bean.getCountry());
        selectedGym=bean;
        resetMembership();
        resetCoupons();
        payCorner.setVisible(false);
        resetCorner.setVisible(false);
    }

    @FXML
    void resetMembership() {
        nameMembership.setText("");
        priceMembership.setText("");
        durationMembership.setText("");
        descriptionMembership.setText("");
        rewardMembership.setText("");
        selectedMembership=null;
        priceFinal.setText("");
        durationFinal.setText("");
        pointsFinal.setText("");
        if(selectedCoupons.isEmpty())resetCorner.setVisible(false);
        payCorner.setVisible(false);
    }

    @FXML
    void resetCoupons() {
            selectedCoupons.clear();
            refreshListViewCoupons();
            if(selectedMembership==null)resetCorner.setVisible(false);
            composeFinalMembership();
    }

    private void refreshListViewCoupons(){
        couponsListview.getItems().clear();
        ManageGenericList.setList(couponsListview,selectedCoupons);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            manageMembershipController=new ManageMembershipController();
        } catch (NoLoggedUserException e) {
            e.callMe(VIEW);
        }
        payCorner.setVisible(false);
        resetCorner.setVisible(false);
        selectedCoupons=new ArrayList<>();
        ManageGenericList.setList(couponsListview,selectedCoupons);
        couponsListview.setCellFactory(new SelectedCouponCellFactory());
    }

    @FXML
    void searchMembership(MouseEvent event) {
        if (selectedGym == null) return;
        List<MembershipBean> beans;
        try {
            beans = manageMembershipController.getMembershipList(selectedGym);
        } catch (NoUserFoundException e) {
            new DBUnrreachableException().callMe(VIEW);
            return;
        } catch (DBUnrreachableException e) {
            e.callMe(VIEW);
            return;
        }
        try {
            SearchMembershipPopUp.getSearchPopUp(this, beans,"SearchMembershipPopUp.fxml",POPUPS,VIEW);
        } catch (IOException e) {
            CostumeLogger.getInstance().logError(e);
        }
    }

    @Override
    public void selectedMembership(MembershipBean bean) {
            selectedMembership=bean;
            nameMembership.setText(bean.getName());
            priceMembership.setText(bean.getPrice() +"$");
            durationMembership.setText(bean.getDurationInDays()+" days");
            descriptionMembership.setText(bean.getDescription());
            rewardMembership.setText(bean.getPointsAwardedOnBuy()+" points");
            payCorner.setVisible(true);
            resetCorner.setVisible(true);
             composeFinalMembership();
    }

    private void composeFinalMembership() {
        if (selectedGym == null || selectedMembership == null) return;
        MembershipBean finalMembership;
        try {
            finalMembership = manageMembershipController.applyCouponsToMembership(selectedGym, selectedMembership, selectedCoupons);
        } catch ( DBUnrreachableException e) {
            e.callMe(VIEW);
            return;
        } catch (NoUserFoundException e) {
            resetCoupons();
            resetMembership();
            selectedGym = null;
            return;
        }catch (MembershipCouponNotFoundException | DecoratorNoBaseComponentException |
                CouponNotCumulativeException e){
            e.callMe(VIEW);
            resetCoupons();
            composeFinalMembership();
            return;
        }
        pointsFinal.setText(finalMembership.getPointsAwardedOnBuy()+" points");
        priceFinal.setText(finalMembership.getPrice()+"$");
        durationFinal.setText(finalMembership.getDurationInDays()+" days");
    }

    @FXML
    void searchCoupons(MouseEvent event) {
        if (selectedGym == null) return;
        List<CouponsBean> beans;
        try {
            beans = manageMembershipController.getCouponsList(selectedGym);
        } catch (NoUserFoundException e) {
            new DBUnrreachableException().callMe(VIEW);
            return;
        } catch (DBUnrreachableException e) {
            e.callMe(VIEW);
            return;
        }
        try {
            SearchCouponPopUp.getSearchPopUp(this, beans,"SearchCouponsPopUp.fxml",POPUPS,VIEW);
        } catch (IOException e) {
            CostumeLogger.getInstance().logError(e);
        }
    }

    @Override
    public void selectedCoupon(CouponsBean bean) {
                selectedCoupons.add(bean);
                refreshListViewCoupons();
                resetCorner.setVisible(true);
                composeFinalMembership();
    }

    @Override
    public void buyMembership() {
        try {
            manageMembershipController.payNewMembership(selectedGym,selectedMembership,selectedCoupons);
        } catch (NoCardFoundException | DBUnrreachableException | MembershipCouponNotFoundException |
                 DecoratorNoBaseComponentException | FailedToSaveNewMembership | MembershipOnlyForNewUserException |
                 CouponNotCumulativeException | DataFieldException | PaymentFailedException e) {
            e.callMe(VIEW);
            return;
        } catch (NoUserFoundException e) {
            MainMenuSingleton.reset();
            e.callMe(VIEW);
            return;
        }
        try {
            MainMenuSingleton.getMainMenu().setActivity("PersonInfoPage.fxml", "home");
        } catch (IOException e) {
            CostumeLogger.getInstance().logError(e);
        }
    }

    @Override
    public void buyMembership(CardInfoBean bean, boolean remember) {
        try {
            manageMembershipController.payNewMebership(selectedGym,selectedMembership,selectedCoupons,bean,remember);
        } catch (MembershipCouponNotFoundException | DecoratorNoBaseComponentException | FailedToSaveNewMembership |
                 MembershipOnlyForNewUserException | PaymentFailedException | CouponNotCumulativeException |
                 DataFieldException e) {
e.callMe(VIEW);        } catch (NoUserFoundException | DBUnrreachableException e) {
            MainMenuSingleton.reset();
            e.callMe(VIEW);
        }
        try {
            MainMenuSingleton.getMainMenu().setActivity("PersonInfoPage.fxml", "home");
        } catch (IOException e) {
            CostumeLogger.getInstance().logError(e);
        }
    }
}


