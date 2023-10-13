package viewone.graphical_controllers.gym;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class GymSomethingGUIController extends Application {
    @Override
    public void start(Stage primaryStage) {
        ListView<String> listView = new ListView<>();

        // Set the custom cell factory
        listView.setCellFactory(param -> new CustomListCell());

        listView.getItems().addAll("Item 1", "Item 2", "Item 3");

        Scene scene = new Scene(listView, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ListView with Button");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}