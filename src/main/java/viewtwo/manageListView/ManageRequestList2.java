package viewtwo.manageListView;

import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
import exceptions.DBUnrreachableException;
import exceptions.dataException.DataFieldException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import viewtwo.manageListView.listCells.RequestListCellFactory2;


public class ManageRequestList2 {

    public static void setRequestList(ListView<RequestBean> requestList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) throws DBUnrreachableException{
        requestList.setCellFactory(new RequestListCellFactory2());
        updateList(requestList, satisfyWorkoutRequestsController);
    }

    public static void updateList(ListView<RequestBean> requestList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) throws DBUnrreachableException {
        ObservableList<RequestBean> requestBeanObservableList = FXCollections.observableList(satisfyWorkoutRequestsController.getTrainerRequests());
        requestList.setItems(FXCollections.observableList(requestBeanObservableList));
    }
}