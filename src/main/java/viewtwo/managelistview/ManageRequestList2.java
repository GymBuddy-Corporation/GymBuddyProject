package viewtwo.managelistview;

import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
import exceptions.DBUnrreachableException;
import exceptions.dataexception.DataFieldException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import viewtwo.managelistview.listcells.RequestListCellFactory2;


public class ManageRequestList2 {

    public static void setRequestList(ListView<RequestBean> requestList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) throws DBUnrreachableException, DataFieldException {
        requestList.setCellFactory(new RequestListCellFactory2());
        updateList(requestList, satisfyWorkoutRequestsController);
    }

    public static void updateList(ListView<RequestBean> requestList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) throws DBUnrreachableException, DataFieldException {
        ObservableList<RequestBean> requestBeanObservableList = FXCollections.observableList(satisfyWorkoutRequestsController.getTrainerRequests());
        requestList.setItems(FXCollections.observableList(requestBeanObservableList));
    }
}