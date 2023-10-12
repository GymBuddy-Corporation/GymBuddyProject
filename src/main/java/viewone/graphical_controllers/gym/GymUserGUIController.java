package viewone.graphical_controllers.gym;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> Lista = new ArrayList<>(Arrays.asList("Luca", "Matteo", "Marco"));
        AthletesList.getItems().addAll(Lista);
    }
}
