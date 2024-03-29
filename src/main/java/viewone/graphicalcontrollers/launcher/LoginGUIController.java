package viewone.graphicalcontrollers.launcher;

import beans.*;
import controllers.UserAccessController;
import engineering.LoggedUserSingleton;
import exceptions.AlreadyLoggedUserException;
import exceptions.DBUnrreachableException;
import exceptions.NoUserFoundException;
import exceptions.dataexception.DataFieldException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utils.SwitchPage;


public class LoginGUIController {



    private static final String COLOR_1 ="45065D";
    private static final String COLOR_2 ="linear-gradient(to bottom, #00e4af, #5d7cf3)";
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button saveButton;
    private boolean saveCredentials=false;

    @FXML
    public void changeStatus(){
        if(saveCredentials) {
            saveButton.setStyle("-fx-background-color: "+ COLOR_1);
            saveCredentials=false;
        }else {
            saveButton.setStyle("-fx-background-color:"+ COLOR_2);
            saveCredentials=true;
        }
    }

    @FXML
    public void goForward() {
        UserAccessController controller=new UserAccessController();
        UserBean userBean;
        try {
            userBean=controller.login(CredentialsBean.ctorWithSyntaxCheck(emailField.getText(),passwordField.getText()),saveCredentials);
        }catch(AlreadyLoggedUserException e){
            userBean = LoggedUserSingleton.getSingleton().getMyBean();
        } catch (DataFieldException | NoUserFoundException | DBUnrreachableException e) {
            e.callMe(1);
            return;
        }
        changePage(userBean);

    }
    private void changePage(UserBean userBean) {
        if (userBean instanceof AthleteBean) {
            SwitchPage.changePage( "AthleteHome.fxml", "athlete", 1);
        } else if (userBean instanceof TrainerBean) {
            SwitchPage.changePage( "PTHome.fxml", "pt", 1);
        } else if(userBean instanceof GymBean) {
            SwitchPage.changePage( "GymHome.fxml", "gym", 1);
        }
    }
    @FXML
    public void goBack()  {
        SwitchPage.changePage( "ChooseActor.fxml", "launcher", 1);
    }




}