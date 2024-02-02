package viewtwo.graphicalcontrollers.pt;

import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
import exceptions.CostumException;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import viewtwo.engegnering.MainMenuSingleton;
import utils.MainStage;
import utils.SwitchPage;
import viewtwo.manageListView.ManageRequestList2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PTHomeGUIController2 implements Initializable {

    @FXML
    private ListView<RequestBean> requestList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeRequestList();
    }

    private void initializeRequestList() {
        try {
            SatisfyWorkoutRequestsController controller = new SatisfyWorkoutRequestsController();
            ManageRequestList2.setRequestList(requestList, controller);
            requestList.getSelectionModel().selectedItemProperty().addListener(
                    (ObservableValue<? extends RequestBean> observable, RequestBean oldItem, RequestBean newItem) -> {
                        if (newItem != null) {
                            handleSelectedRequest(newItem);
                        }
                    }
            );
        }catch (CostumException e){
            try {
                e.callMe(2);
                throw new RuntimeException(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void handleSelectedRequest(RequestBean newItem) {
        try {
            PTSingleRequestGUIController2 controller = (PTSingleRequestGUIController2) MainMenuSingleton.getMainMenu().setActivity("ptSingleRequest.fxml", "pt");
            controller.setStuff(newItem);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    public void manageCommunication() throws Exception {
        MainMenuSingleton.getMainMenu().setActivity("ManageCommunication.fxml", "pt");
    }

    @FXML
    public void manageCourse() throws Exception {
        SwitchPage.setStage(MainStage.getStage(), "ManageCourse.fxml", "pt", 1);
    }
}
