package viewone.graphicalcontrollers.launcher.gym;

import utils.MainStage;
import utils.SwitchPage;
import javafx.fxml.FXML;

public class GymFirstGUIController {
    private final String launcher = "launcher";
    private String typeUser;

    @FXML
    protected void login() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"Login.fxml",launcher,1);
    }

    public void setValue(String user){
        this.typeUser = user;
    }

    @FXML protected void registration() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"GymRegistration.fxml",launcher,1);
    }

    @FXML public void getInfo() throws Exception {
        //TODO organizza il bottone info
    }

    @FXML public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"ChooseActor.fxml",launcher,1);
    }

}
