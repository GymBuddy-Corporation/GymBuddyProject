package viewone.graphicalcontrollers.athlete;

import beans.GymInfoBean;
import beans.SearchGymBean;
import controllers.ManageMembershipController;
import controllers.UserAccessController;
import exceptions.NoLoggedUserException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import utils.MainStage;
import utils.SwitchPage;
import viewone.managelistview.ManageGymList;
import viewone.managelistview.interfaces.GymListGUIInterface;
import viewone.managelistview.listCells.GymListCellFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManageMembershipSearchGymGUIController implements Initializable, GymListGUIInterface {
    @FXML
    ListView<GymInfoBean> gymList;
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
        ManageGymList.setGymList(gymList,list);
        gymList.setCellFactory(new GymListCellFactory());
        ManageGymList.setListnere(gymList,this);
       }

    private List<GymInfoBean> searchWithFilter(SearchGymBean filter){
        ManageMembershipController controller=null;
        try {
            controller = new ManageMembershipController();
        } catch (NoLoggedUserException e) {
            try {
                e.callMe(1);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        return  controller.searchGym(filter);

    }

    public void searchGyms(){
        gymList.getItems().clear();
        String name=nameField.getText();
        String country=countryField.getText();
        String add= addressField.getText();
        String city= cityField.getText();
        List <GymInfoBean> list=searchWithFilter(new SearchGymBean(name,city,add,country));
        ManageGymList.setGymList(gymList,list);
        ManageGymList.setListnere(gymList,this);
    }


    @Override
    public void setInfo(GymInfoBean bean) {
                selectedCityLabel.setText(bean.getCity());
                selectedCountryLabel.setText(bean.getCountry());
                selectedNameLabel.setText(bean.getName());
                selectedAddressLabel.setText(bean.getAddress());
                selectedButton.setVisible(true);
    }
}
