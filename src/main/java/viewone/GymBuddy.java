package viewone;

import utils.MainStage;
import utils.SwitchPage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class GymBuddy extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainStage.setStage(stage); //Va fatto una sola volta siccome è static, quindi una volta lanciata l'applicazione non serve cambiarlo
        SwitchPage.setStage(MainStage.getStage(),"hello-viewone.fxml","launcher",1);
    }

    public static void main(String[] args) {
        launch();
    }
}