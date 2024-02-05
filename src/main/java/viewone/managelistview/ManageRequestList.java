package viewone.managelistview;

import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
import exceptions.DBUnrreachableException;
import viewone.managelistview.listcells.RequestListCellFactory;
import exceptions.dataexception.DataFieldException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;


public class ManageRequestList {

    public static void setRequestList(ListView<RequestBean> requestList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) throws DataFieldException, DBUnrreachableException {
        requestList.setCellFactory(new RequestListCellFactory());
        updateList(requestList, satisfyWorkoutRequestsController);
    }

    public static void updateList(ListView<RequestBean> requestList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) throws DBUnrreachableException, DataFieldException {
        ObservableList<RequestBean> requestBeanObservableList = FXCollections.observableList(satisfyWorkoutRequestsController.getTrainerRequests());
        requestList.setItems(FXCollections.observableList(requestBeanObservableList));
    }
}