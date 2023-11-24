package viewone.graphicalControllers.launcher;

import utils.MainStage;
import utils.SwitchPage;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

import java.io.IOException;

public class GeneralGUIController {

    //passaggio da hello a choose actor

    @FXML protected void goForward(ActionEvent event) throws IOException {
        SwitchPage.setStage(MainStage.getStage(),"ChooseActor.fxml","launcher",1);
    }

}
