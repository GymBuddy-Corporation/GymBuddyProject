package viewone.graphical_controllers.launcher.pt;

import utils.MainStage;
import utils.SwitchPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PTLoginGUIController {
    @FXML
    public void goForward() throws Exception {
        String path = "/viewone/pt/PTHome.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }

    @FXML
    public void goBack() throws Exception {
       String path = "/viewone/launcher/ChooseActor.fxml";
       SwitchPage.setStage(MainStage.stage,path);
    }


    public void getInfo(MouseEvent event) throws Exception {
        //TODO Connected with the LogoInfoButton
    }
}
