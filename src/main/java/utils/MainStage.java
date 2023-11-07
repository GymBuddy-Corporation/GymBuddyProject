package utils;

import javafx.stage.Stage;

public class MainStage {
    private static  Stage stage;
    private MainStage(){
        //Per ora niente
    }
     public static Stage getStage(){
        return stage;
     }
     public static void setStage(Stage stage){
        MainStage.stage=stage;
    }
}
