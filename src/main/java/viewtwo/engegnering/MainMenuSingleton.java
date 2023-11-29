package viewtwo.engegnering;

import javafx.fxml.FXMLLoader;
import utils.SwitchPage;
import viewtwo.graphicalcontrollers.home.MainMenuController;

import java.io.IOException;

public class MainMenuSingleton {
    private static MainMenuSingleton me;
    private MainMenuController controller;
    private MainMenuSingleton(MainMenuController controller){
        this.controller=controller;
    }
    public static void createMainMenu(MainMenuController controller){
        if(MainMenuSingleton.me==null){
                me=new MainMenuSingleton(controller);
        }
        //return me;
    }
    public static MainMenuSingleton getMainMenu(){
        return me;
    }

    public void setActivity(String name,String folder) throws IOException {
        FXMLLoader fx=new FXMLLoader(SwitchPage.class.getResource(SwitchPage.getpath(name,folder,2)));
        controller.setActivity(fx.load());
    }

}
