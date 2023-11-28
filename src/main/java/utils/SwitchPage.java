package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

import static java.lang.System.exit;

public class SwitchPage {

    private SwitchPage(){
        //nothing for now
    }

    public static void logOff() {
        try {
            SwitchPage.setStage(MainStage.getStage(),"Login.fxml","launcher",1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static int x=800;
    static int y=500;

    private static final String SEP="/";
    private static final String VIEWONE="viewone";
    private static final String VIEWTWO="viewtwo";
    public static  Scene setStage(Stage stage, String path){//TODO: REmove after changing all calls to it
        String temp=stage+path;
        System.out.println(temp);
        return null;
    }
    public static  Object setStage(Stage stage, String name,String folder,int view) throws IOException {
        String path;
        path=SwitchPage.getpath(name,folder,view);
        FXMLLoader fxmlLoader = new FXMLLoader(SwitchPage.class.getResource(path));
        Parent load = fxmlLoader.load();
        Scene scena = new Scene(load, x, y);
        stage.getIcons().add(new javafx.scene.image.Image("LogoSoloImageWhite.png"));
        stage.setResizable(false);
        stage.setScene(scena);
        stage.show();
        return fxmlLoader.getController();
    }
    public static String getpath(String name,String folder,int view){
        String view_path="";
        switch (view){
            case 1:
                view_path=VIEWONE;
                break;
            case 2:
                view_path=VIEWTWO;
                break;
            default:
                //TODO:trhoware un exception quando capiro come fare
                exit(-1);
                break;
        }
        return SEP+view_path+SEP+folder+SEP+name;}
}
