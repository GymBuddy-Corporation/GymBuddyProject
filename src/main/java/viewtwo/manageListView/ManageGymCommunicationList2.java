package viewtwo.manageListView;

import controllers.SeeCommunications;
import javafx.scene.control.ListView;

import java.util.List;

public class ManageGymCommunicationList2 {

    private ManageGymCommunicationList2() {
    }

    public static void setCommunicationList(ListView<String> communicationList) {
        SeeCommunications seeCommunications = new SeeCommunications();
        List<String> comList = seeCommunications.getGymCommunications();
        communicationList.getItems().addAll(comList);
    }
}
