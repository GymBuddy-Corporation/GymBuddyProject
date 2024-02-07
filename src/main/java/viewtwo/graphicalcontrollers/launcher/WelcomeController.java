package viewtwo.graphicalcontrollers.launcher;

import controllers.UserAccessController;
import engineering.LoggedUserSingleton;
import exceptions.AlreadyLoggedUserException;
import exceptions.DBUnrreachableException;
import exceptions.NoUserFoundException;
import javafx.fxml.FXML;
import utils.MainStage;
import utils.SwitchPage;
import viewtwo.engegnering.MainMenuSingleton;
import viewtwo.graphicalcontrollers.home.MainMenuController;

import java.io.IOException;

public class WelcomeController {
    @FXML
    public void clicked() throws IOException{
        try{
            UserAccessController controller=new UserAccessController();
            controller.loginDeserialization();
            afterLogin();
        } catch (DBUnrreachableException | NoUserFoundException | AlreadyLoggedUserException e) {
            SwitchPage.setStage(MainStage.getStage(),"loginPage.fxml","launcher",2);

        }



    }

    static void afterLogin() throws IOException {
        MainMenuController a = (MainMenuController) SwitchPage.setStage(MainStage.getStage(), "mainMenu.fxml", "home", 2);
        MainMenuSingleton.createMainMenu(a);
        switch (LoggedUserSingleton.getSingleton().getUserType()) {
            case PT ->        MainMenuSingleton.getMainMenu().setActivity("ptHome.fxml", "pt");
            case GYM ->         MainMenuSingleton.getMainMenu().setActivity("gymUsersHome.fxml", "gym");
            case ATHLETE ->          MainMenuSingleton.getMainMenu().setActivity("PersonInfoPage.fxml", "home");
            case null, default ->  MainStage.getStage().close();

        }
    }
}
