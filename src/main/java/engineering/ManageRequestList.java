package engineering;

import beans.AthleteBean;
import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
/*
import exceptions.DBUnreachableException;
*/
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/*
import java.sql.SQLException;
*/

public class ManageRequestList {

    private ManageRequestList() {}

    public static void setRequestList(ListView<RequestBean> requestList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController)/* throws SQLException, DBUnreachableException */{

        AthleteBean athlete1 = new AthleteBean("LuX71");
        RequestBean r1 = new RequestBean("Ciao, vorrei una nuova scheda che" +
                " aiutasse a crescere principalmente di spalle", athlete1, "MRTLCU00P01D612J");
        AthleteBean athlete2 = new AthleteBean("Mario Regine");
        RequestBean r2 = new RequestBean("Ei vorrei un piano che mi" +
                " aiutasse a sviluppare il petto", athlete2, "MRTLCU00P01D612J");
        AthleteBean athlete3 = new AthleteBean("Edoardo Manenti");
        RequestBean r3 = new RequestBean("Ciao, potresti organizzarmi una scheda" +
                " senza allenare gambe?", athlete3, "MRTLCU00P01D612J");
        requestList.getItems().addAll(r1, r2, r3);
        requestList.setCellFactory(new RequestListCellFactory());
        /*updateList(requestList, satisfyWorkoutRequestsController);*/
    }

    /*public static void updateList(ListView<RequestBean> requestList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) *//*throws SQLException, DBUnreachableException *//*{
        ObservableList<RequestBean> requestBeanObservableList = FXCollections.observableList(satisfyWorkoutRequestsController.getTrainerRequests());
        requestList.setItems(FXCollections.observableList(requestBeanObservableList));
    }*/
}
