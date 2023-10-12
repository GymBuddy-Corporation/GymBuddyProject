package viewone.graphical_controllers.launcher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewone.GymBuddy;

import java.net.URL;
import java.util.ResourceBundle;


public class ChooseActorGUIController implements Initializable {

    @FXML private RadioButton gym;
    @FXML private RadioButton pt;
    @FXML private RadioButton user;


    public void goForward(MouseEvent event) throws Exception {
        Stage newStage = new Stage();
        if(gym.isSelected()) {
            FXMLLoader fxmlLoader = new FXMLLoader(GymBuddy.class.getResource("/viewone/launcher/GymFirst.fxml"));
            stampa(newStage, fxmlLoader);
        } else if (pt.isSelected()) {
            FXMLLoader fxmlLoader = new FXMLLoader(GymBuddy.class.getResource("/viewone/launcher/ChooseActor.fxml"));
            stampa(newStage, fxmlLoader);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(GymBuddy.class.getResource("/viewone/launcher/ChooseActor.fxml"));
            stampa(newStage, fxmlLoader);
        }
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

    }

    public void changeLanguage(MouseEvent event){
        //TODO gestisci il bottone con il pop sulla scelta della lingua (il cambio è dummy)
    }

    public void stampa(Stage newStage, FXMLLoader fxmlLoader) throws Exception {
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        newStage.setTitle("GymBuddy");
        newStage.setResizable(false);
        newStage.setScene(scene);
        newStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group = new ToggleGroup();
        gym.setToggleGroup(group);
        pt.setToggleGroup(group);
        user.setToggleGroup(group);
        gym.fire();
    }
}
