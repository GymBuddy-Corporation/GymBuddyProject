package viewtwo.graphicalcontrollers.launcher;

import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

import java.io.IOException;

public class WelcomeController {
    @FXML
    public void clicked() throws IOException {
        SwitchPage.setStage(MainStage.getStage(),"loginPage.fxml","launcher",2);
    }
}
