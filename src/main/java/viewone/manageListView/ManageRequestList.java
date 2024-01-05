package viewone.manageListView;

import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
import viewone.manageListView.listCells.RequestListCellFactory;
import exceptions.dataException.DataFieldException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;


public class ManageRequestList {

    public static void setRequestList(ListView<RequestBean> requestList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) throws DataFieldException/* throws SQLException, DBUnreachableException */{
        requestList.setCellFactory(new RequestListCellFactory());
        updateList(requestList, satisfyWorkoutRequestsController);
    }

    //TODO sistema password null bug

    public static void updateList(ListView<RequestBean> requestList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) throws DataFieldException /*throws SQLException, DBUnreachableException */{
        ObservableList<RequestBean> requestBeanObservableList = FXCollections.observableList(satisfyWorkoutRequestsController.getTrainerRequests());
        requestList.setItems(FXCollections.observableList(requestBeanObservableList));
    }
}