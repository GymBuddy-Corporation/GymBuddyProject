package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.HashMap;

import static java.lang.System.exit;
class Old_scene{
    Scene scene;
    Object controller;
    Old_scene(Scene a,Object b){
        scene=a;
        controller=b;
    }
}
public class SwitchPage {



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



    private final HashMap<String, Old_scene> remembered_scenes;

    private SwitchPage(){
        remembered_scenes=new HashMap<String,Old_scene>();
    }

    private static SwitchPage me=null;

    public static void saveElement(String name,String folder,Scene scene,Object controller){
        if(me==null) {
            me = new SwitchPage();
        }
        if(me.remembered_scenes.get(name+folder)!=null)me.remembered_scenes.remove(name+folder);
        me.remembered_scenes.put(name+folder,new Old_scene(scene,controller));
    }
    public static Object getController(String name,String folder){
        if(me==null){
            return  null;
        }
        if(me.remembered_scenes.get(name+folder)==null)return null;
        return me.remembered_scenes.get(name+folder).controller;
    }


    public static  Object setStage(Stage stage, String name,String folder,int view) throws IOException {
        if(me!=null){
            if(me.remembered_scenes.get(name+folder)!=null){
                Old_scene old_scene=me.remembered_scenes.get(name+folder);
                stage.setScene(old_scene.scene);
                stage.show();
                return old_scene.controller;
            }
        }
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
        String viewPath="";
        switch (view){
            case 1:
                viewPath=VIEWONE;
                break;
            case 2:
                viewPath=VIEWTWO;
                break;
            default:
                //TODO:trhoware un exception quando capiro come fare
                exit(-1);
                break;
        }
        return SEP+viewPath+SEP+folder+SEP+name;}
}
