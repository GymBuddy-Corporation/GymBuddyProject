package viewtwo.graphicalcontrollers.home;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MainMenuController {
    @FXML
    Pane paneSfondo;

    public void setActivity(Node e){
        paneSfondo.getChildren().add(e);
    }

}
