package viewone.graphicalcontrollers.athlete;

import beans.GymInfoBean;
import beans.SearchGymBean;
import controllers.ManageMembershipController;
import controllers.UserAccessController;
import exceptions.CostumException;
import exceptions.DBUnrreachableException;
import exceptions.NoLoggedUserException;
import exceptions.NoUserFoundException;
import exceptions.logger.CostumeLogger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import utils.MainStage;
import utils.SwitchPage;
import utils.listView.ManageGenericList;
import utils.listView.SetInfoListViewInterface;
import viewone.managelistview.listCells.GymListCellFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class ManageMembershipSearchGymGUIController implements Initializable, SetInfoListViewInterface {
    @FXML
    ListView<Object> gymList;
    @FXML
    Text selectedNameLabel;
    @FXML
    Text selectedCityLabel;
    @FXML
    Text selectedCountryLabel;
    @FXML
    Text selectedAddressLabel;
    @FXML
    Button selectedButton;
    @FXML
    TextField nameField;
    @FXML
    TextField addressField;
    @FXML
    TextField cityField;
    @FXML
    TextField countryField;




    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"AthleteHome.fxml","athlete",1);
    }
    public void logout() throws Exception {
        UserAccessController controller=new UserAccessController();
        controller.logout();
        SwitchPage.setStage(MainStage.getStage(),"Login.fxml","launcher",1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List <GymInfoBean> list=searchWithFilter(new SearchGymBean("","","",""));
        ManageGenericList.setList(gymList,list);
        gymList.setCellFactory(new GymListCellFactory());
        ManageGenericList.setListnere(gymList,this);
        selectedButton.setVisible(false);

    }

    private List<GymInfoBean> searchWithFilter(SearchGymBean filter){
        ManageMembershipController controller=null;
        try {
            controller = new ManageMembershipController();
            return  controller.searchGym(filter);
        } catch (NoLoggedUserException | DBUnrreachableException e) {
            try {
                e.callMe(1);
            } catch (IOException ex) {
                CostumeLogger.getInstance().logError(ex);
            }
        }
        return Collections.emptyList();
    }

    public void searchGyms(){
        gymList.getItems().clear();
        String name=nameField.getText();
        String country=countryField.getText();
        String add= addressField.getText();
        String city= cityField.getText();
        List <GymInfoBean> list=searchWithFilter(new SearchGymBean(name,city,add,country));
        ManageGenericList.setList(gymList,list);
        ManageGenericList.setListnere(gymList,this);
    }

    GymInfoBean bean;
    @Override
    public void setInfo(Object item) {
                bean=(GymInfoBean) item;
                selectedCityLabel.setText(bean.getCity());
                selectedCountryLabel.setText(bean.getCountry());
                selectedNameLabel.setText(bean.getName());
                selectedAddressLabel.setText(bean.getAddress());
                selectedButton.setVisible(true);
    }
@FXML
    public void nextPage() {
    if (bean == null) return;
    ManageMembershipCreateMembershipGUIController controller = null;
    try {
        controller = (ManageMembershipCreateMembershipGUIController) SwitchPage.setStage(MainStage.getStage(), "ManageMembershipCreateMembershipGui.fxml", "athlete", 1);
        try {
            controller.initialize(bean);
        } catch (NoLoggedUserException e) {
            SwitchPage.setStage(MainStage.getStage(),"Login.fxml", "launcher",1);

        } catch (NoUserFoundException e) {
            (new CostumException("The gym doesn't exit")).callMe(1);
        }
    } catch (IOException e) {
        CostumeLogger.getInstance().logError(e);}

}
}
