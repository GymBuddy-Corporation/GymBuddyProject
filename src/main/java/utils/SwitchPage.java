package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SwitchPage {

    private SwitchPage(){
        //nothing for now
    }
    static int x=800;
    static int y=500;
    public static  Scene setStage(Stage stage, String toscene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SwitchPage.class.getResource(toscene));
        Parent load = fxmlLoader.load();
        Scene scena = new Scene(load, x, y);
        stage.setResizable(false);
        stage.setScene(scena);
        stage.show();
        return scena;
    }
}
