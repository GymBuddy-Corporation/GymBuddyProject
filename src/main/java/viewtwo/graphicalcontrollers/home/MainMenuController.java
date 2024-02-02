package viewtwo.graphicalcontrollers.home;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.NoSuchElementException;

public class MainMenuController {
    @FXML
    Pane paneSfondo;

    public void setActivity(Node e){
        try{
        paneSfondo.getChildren().removeFirst();
        }catch(NoSuchElementException ignored){}
        paneSfondo.getChildren().add(e);
    }

}
