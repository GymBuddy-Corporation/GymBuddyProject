package engineering.manageListView;

import beans.RequestBean1;
import controllers.SatisfyWorkoutRequestsController;
/*
import exceptions.DBUnreachableException;
*/
import engineering.manageListView.listCells.RequestListCellFactory;
import exceptions.dataException.DataFieldException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/*import java.sql.SQLException;*/

public class ManageRequestList {

    public static void setRequestList(ListView<RequestBean1> requestList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) throws DataFieldException/* throws SQLException, DBUnreachableException */{
        requestList.setCellFactory(new RequestListCellFactory());
        updateList(requestList, satisfyWorkoutRequestsController);
    }

    //TODO chiarisci perchè cazzo mi mostra le richieste senz update, mentre non me le mostra se ho update
    //TODO sistema password null bug

    public static void updateList(ListView<RequestBean1> requestList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) throws DataFieldException /*throws SQLException, DBUnreachableException */{
        ObservableList<RequestBean1> requestBeanObservableList = FXCollections.observableList(satisfyWorkoutRequestsController.getTrainerRequests());
        requestList.setItems(FXCollections.observableList(requestBeanObservableList));
    }
}