package viewtwo.graphicalcontrollers.pt;

import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
import exceptions.DBUnrreachableException;
import exceptions.NoLoggedUserException;
import exceptions.dataexception.DataFieldException;
import exceptions.logger.CostumeLogger;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import viewtwo.engegnering.MainMenuSingleton;
import utils.MainStage;
import utils.SwitchPage;
import viewtwo.managelistview.ManageRequestList2;

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
        SatisfyWorkoutRequestsController controller;
        try {
            controller = new SatisfyWorkoutRequestsController();
            ManageRequestList2.setRequestList(requestList, controller);
        } catch (NoLoggedUserException | DataFieldException | DBUnrreachableException e) {
            e.callMe(2);
            return;
        }

        requestList.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends RequestBean> observable, RequestBean oldItem, RequestBean newItem) -> {
                    if (newItem != null) {
                        handleSelectedRequest(newItem);
                    }
                }
        );
    }

    private void handleSelectedRequest(RequestBean newItem) {
        try {
            PTSingleRequestGUIController2 controller = (PTSingleRequestGUIController2) MainMenuSingleton.getMainMenu().setActivity("ptSingleRequest.fxml", "pt");
            controller.setStuff(newItem);
        } catch (IOException e) {
            CostumeLogger.getInstance().logError(e);
        }
    }

    @FXML
    public void manageCommunication() throws Exception {
        MainMenuSingleton.getMainMenu().setActivity("ManageCommunication.fxml", "pt");
    }

    public void refreshList() {
        try {
            SatisfyWorkoutRequestsController controller = new SatisfyWorkoutRequestsController();
            ManageRequestList2.updateList(requestList, controller);
        } catch (DataFieldException | DBUnrreachableException | NoLoggedUserException e) {
            e.callMe(2);
        }
    }
}
