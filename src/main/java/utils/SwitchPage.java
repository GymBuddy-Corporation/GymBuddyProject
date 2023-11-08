package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import static java.lang.System.exit;

public class SwitchPage {

    private SwitchPage(){
        //nothing for now
    }
    static int x=800;
    static int y=500;

    private static String SEP="/";
    private static String VIEWONE="viewone";
    public static  Scene setStage(Stage stage, String path){
        return null;
    }
    public static  Scene setStage(Stage stage, String name,String folder,int view) throws IOException {
        String path;
        String view_path="";
        switch (view){
            case 1:
                view_path=VIEWONE;
                break;

            default:
                //TODO:trhoware un exception quando capiro come fare
                exit(0);
            break;
        }
        path=SEP+view_path+SEP+folder+SEP+name;
        FXMLLoader fxmlLoader = new FXMLLoader(SwitchPage.class.getResource(path));
        Parent load = fxmlLoader.load();
        Scene scena = new Scene(load, x, y);
        stage.setResizable(false);
        stage.setScene(scena);
        stage.show();
        return scena;
    }
}
