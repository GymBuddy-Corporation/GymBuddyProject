package utils;

import exceptions.logger.CostumeLogger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

import static java.lang.System.exit;
class OldScene{
    Scene scene;
    Object controller;
    OldScene(Scene a,Object b){
        scene=a;
        controller=b;
    }
}
public class SwitchPage {


    private static final String SEP="/";
    private static final String VIEWONE="viewone";
    private static final String VIEWTWO="viewtwo";
    static int x=800;
    static int y=500;
    private static SwitchPage me=null;
    private final HashMap<String, OldScene> oldSceneHashMap;
    private SwitchPage(){
        oldSceneHashMap =new HashMap<String,OldScene>();
    }

    public static Object changePage(String file,String folder,int view){
        try {
            return SwitchPage.setStage(MainStage.getStage(),file,folder,view);
        } catch (IOException e) {
            CostumeLogger.getInstance().logError(e);
        }
        return null;
    }

    public static  Object setStage(Stage stage, String name,String folder,int view) throws IOException {
        if(me!=null && me.oldSceneHashMap.get(name+folder)!=null){
                OldScene oldScene=me.oldSceneHashMap.get(name+folder);
                stage.setScene(oldScene.scene);
                stage.show();
                return oldScene.controller;

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
                exit(-1);
                break;
        }
        return SEP+viewPath+SEP+folder+SEP+name;}

    public static void saveElement(String name,String folder,Scene scene,Object controller){
        if(me==null) {
            me = new SwitchPage();
        }
        if(me.oldSceneHashMap.get(name+folder)!=null)me.oldSceneHashMap.remove(name+folder);
        me.oldSceneHashMap.put(name+folder,new OldScene(scene,controller));
    }

    public static void deleteElement(String name,String folder){
        if(me==null) {
            me = new SwitchPage();
        }
        if(me.oldSceneHashMap.get(name+folder)!=null)me.oldSceneHashMap.remove(name+folder);
    }

    public static Object getController(String name,String folder){
        if(me==null){
            return  null;
        }
        if(me.oldSceneHashMap.get(name+folder)==null)return null;
        return me.oldSceneHashMap.get(name+folder).controller;
    }
}
