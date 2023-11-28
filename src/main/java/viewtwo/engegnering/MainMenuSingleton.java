package viewtwo.engegnering;

import javafx.fxml.FXMLLoader;
import utils.SwitchPage;
import viewtwo.graphicalcontrollers.home.MainMenuController;

import java.io.IOException;

public class MainMenuSingleton {
    private static MainMenuSingleton me;
    private MainMenuController controller;
    private MainMenuSingleton(MainMenuController controller){
        MainMenuSingleton.me=this;
        this.controller=controller;
    }
    public static MainMenuSingleton getMainMenu(MainMenuController controller){
        if(MainMenuSingleton.me==null){
            new MainMenuSingleton(controller);
        }
        return me;
    }

    public void setActivity(String name,String folder) throws IOException {
        FXMLLoader fx=new FXMLLoader(SwitchPage.class.getResource(SwitchPage.getpath(name,folder,2)));
        controller.setActivity(fx.load());
    }

}
