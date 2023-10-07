package viewone;
//Classe messa per il cambio da una scena ad un'altra, ma da sistemare

import engineering.MainPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class SwitchPage {
    private static final String EXTENSION = ".fxml";
    public static final String SRC_MAIN_RESOURCES_VIEWONE = "src/main/resources/viewone/";
    public static final String SLASH = "/";

    private SwitchPage(){}

    public static Object switchPage(String fileName, String pathString) throws IOException {
        try{
            URL fileUrl;
            if(pathString.isEmpty()) {
                fileUrl = new File(SRC_MAIN_RESOURCES_VIEWONE + fileName + EXTENSION).toURI().toURL();
            }
            else {
                fileUrl = new File(SRC_MAIN_RESOURCES_VIEWONE + pathString + SLASH + fileName + EXTENSION).toURI().toURL();
            }
            FXMLLoader root = new FXMLLoader(fileUrl);
            Pane view = root.load();
            MainPane.getInstance().setCenter(view); //controlla sto coso
            return root.getController();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        /*catch (LoadException e) {
            if(e.getCause() instanceof DBUnreachableException) {
                logOff();
            } else {
                e.printStackTrace();
                throw new FatalErrorException();
            }
        }
        return null;*/
    }

    /*public static void logOff() {
        try {
            LoggedUserSingleton.resetUserInfo();
            switchPage("Login", "launcher");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


}
