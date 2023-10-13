package viewone;

import Nome_a_caso.MainStage;
import Nome_a_caso.SwitchPage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class GymBuddy extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainStage.stage=stage; //Va fatto una sola volta siccome è static, quindi una volta lanciata l'applicazione non serve cambiarlo
        SwitchPage.setStage(MainStage.stage,"/viewone/launcher/hello-viewone.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}