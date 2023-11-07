package viewtwo;

import javafx.application.Application;
import javafx.stage.Stage;
import utils.MainStage;
import utils.SwitchPage;

import java.io.IOException;

public class GymBuddy extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainStage.setStage(stage); //Va fatto una sola volta siccome è static, quindi una volta lanciata l'applicazione non serve cambiarlo
        SwitchPage.setStage(MainStage.getStage(),"/viewone/launcher/hello-viewone.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}