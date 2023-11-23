package engineering;

import beans.AthleteBean;
import beans.CredentialsBean;
import beans.PersonalInfoBean;
import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
/*
import exceptions.DBUnreachableException;
*/
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.time.LocalDate;

/*import java.sql.SQLException;*/

public class ManageRequestList {

    private ManageRequestList() {}

    public static void setRequestList(ListView<RequestBean> requestList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController)/* throws SQLException, DBUnreachableException */{

        PersonalInfoBean pi1 = new PersonalInfoBean("Luca", "Martorelli", LocalDate.of(2000, 9, 1), "MRTLCU00P01D612J", 'm');
        PersonalInfoBean pi2 = new PersonalInfoBean("Matteo", "Martorelli", LocalDate.of(2002, 9, 23), "MRTLCU00P01D612J", 'm');
        PersonalInfoBean pi3 = new PersonalInfoBean("Marco", "Martorelli", LocalDate.of(2007, 9, 6), "MRTLCU00P01D612J", 'm');
        PersonalInfoBean pi4 = new PersonalInfoBean("Alexandru", "Nazare", LocalDate.of(2002, 5, 15), "MRTLCU00P01D612J", 'm');
        PersonalInfoBean pi5 = new PersonalInfoBean("Alessandro", "Cortese", LocalDate.of(1999, 6, 1), "MRTLCU00P01D612J", 'm');

        AthleteBean athlete1 = new AthleteBean("LuX71", pi1,
                CredentialsBean.ctorWithSyntaxCheck("lucam0109@gmail.com", "SSLAZIO1900"));
        RequestBean r1 = new RequestBean("Ciao, vorrei una nuova scheda che" +
                " aiutasse a crescere principalmente di spalle", athlete1, "MRTLCU00P01D612J");
        AthleteBean athlete2 = new AthleteBean("accroccoman", pi2,
                CredentialsBean.ctorWithSyntaxCheck("accroccoman@gmail.com", "megliololiodiItri"));
        RequestBean r2 = new RequestBean("Ei vorrei un piano che mi" +
                " aiutasse a sviluppare il petto", athlete2, "MRTLCU00P01D612J");
        AthleteBean athlete3 = new AthleteBean("EdoMan00", pi3,
                CredentialsBean.ctorWithSyntaxCheck("edoman00@gmail.com", "cyber"));
        RequestBean r3 = new RequestBean("Ciao, potresti organizzarmi una scheda" +
                " pesantissima che mi hanno appena lasciato? Sono disposto a venire in palestra 7/7", athlete3, "MRTLCU00P01D612J");
        requestList.getItems().addAll(r1, r2, r3);
        requestList.setCellFactory(new RequestListCellFactory());
        /*updateList(requestList, satisfyWorkoutRequestsController);*/
    }

    /*public static void updateList(ListView<RequestBean> requestList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) *//*throws SQLException, DBUnreachableException *//*{
        ObservableList<RequestBean> requestBeanObservableList = FXCollections.observableList(satisfyWorkoutRequestsController.getTrainerRequests());
        requestList.setItems(FXCollections.observableList(requestBeanObservableList));
    }*/
}
