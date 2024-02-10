package viewone.graphicalcontrollers.pt;

import javafx.fxml.FXML;
import utils.SwitchPage;

public class ManageCoursesGUIController {
    @FXML
    public void addCourse(){
        //not implemented
    }
    @FXML
    void logout()  {
        SwitchPage.changePage( "Login.fxml", "launcher", 1);
    }
    @FXML
    void goBack() {
        SwitchPage.changePage("PTHome.fxml","pt",1);
    }
}
