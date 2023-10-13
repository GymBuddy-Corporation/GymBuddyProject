package viewone.graphical_controllers.gym;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class GymUserGUIController implements Initializable {

    @FXML private ListView<String> AthletesList;

    public void Scroll(){

    //TODO Successivamente qui andremo a sistemare la visualizzazione da database degli atleti allo scroll

    }

    @FXML
    public void goBack(MouseEvent event) throws Exception {
        String path = "/viewone/launcher/GymFirst.fxml";
        stampa(event, path);
    }
    @FXML
    public void stampa(MouseEvent event, String path) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setTitle("GymBuddy");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> Lista = new ArrayList<>(Arrays.asList("Luca", "Matteo", "Marco"));
        AthletesList.getItems().addAll(Lista);
    }
}
