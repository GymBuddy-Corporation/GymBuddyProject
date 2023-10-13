package viewone.graphical_controllers.pt;

import Nome_a_caso.MainStage;
import Nome_a_caso.SwitchPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import viewone.GymBuddy;

public class PTHomeGUIController {
    @FXML
    public void manageAthletes(ActionEvent event) throws Exception {
        Stage newStage = new Stage();
        String path="/viewone/gym/GymUserView.fxml";
        SwitchPage.setStage(MainStage.stage,path);

    }


}
