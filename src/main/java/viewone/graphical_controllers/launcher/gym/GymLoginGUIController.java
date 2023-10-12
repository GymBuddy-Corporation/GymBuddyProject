package viewone.graphical_controllers.launcher.gym;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewone.GymBuddy;

public class GymLoginGUIController {

    @FXML
    public void goForward(MouseEvent event) throws Exception {
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(GymBuddy.class.getResource("/viewone/gym/GymHome.fxml"));
        stampa(newStage, fxmlLoader);
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    public void goBack(MouseEvent event) throws Exception {
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(GymBuddy.class.getResource("/viewone/launcher/GymFirst.fxml"));
        stampa(newStage, fxmlLoader);
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    public void stampa(Stage newStage, FXMLLoader fxmlLoader) throws Exception {
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        newStage.setTitle("GymBuddy");
        newStage.setResizable(false);
        newStage.setScene(scene);
        newStage.show();
    }
    public void getInfo(MouseEvent event) throws Exception {

    }


}
