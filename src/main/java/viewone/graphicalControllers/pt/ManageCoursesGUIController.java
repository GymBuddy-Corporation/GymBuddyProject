package viewone.graphicalControllers.pt;

import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;

public class ManageCoursesGUIController {

    @FXML
    public void addCourse() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"AddCourse.fxml","pt",1);
    }

    @FXML
    public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(), "PTLogin.fxml", "launcher", 1);
    }
    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }
}
