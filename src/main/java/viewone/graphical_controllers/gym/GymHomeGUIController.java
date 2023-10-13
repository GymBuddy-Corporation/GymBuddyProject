package viewone.graphical_controllers.gym;

import Nome_a_caso.MainStage;
import Nome_a_caso.SwitchPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewone.GymBuddy;

public class GymHomeGUIController {

    @FXML
    public void manageAthletes(ActionEvent event) throws Exception {
        String path = "/viewone/gym/GymUserView.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }




}
