package viewone.graphical_controllers.launcher.gym;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import viewone.GymBuddy;

public class GymRegistrationGUIController {

    @FXML private Button nextButton;
    @FXML private TextField passwField;
    @FXML private TextField passwSField;
    @FXML private TextField address;
    @FXML private TextField name;
    @FXML private TextField email;


    /*Gestire gli inserimenti nei text field: serve sapere il concetto di bean che vedremo più avanti

    public void ottieniCredenziali() {
        String emailText = email.getText();
        String nameText = name.getText();
        String addressText = address.getText();
        String emailText = email.getText();
        System.out.println("Testo inserito: " + emailText);
    }*/


    public void goForward() throws Exception {
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(GymBuddy.class.getResource("/viewone/gym/GymHomeView.fxml"));
        stampa(newStage, fxmlLoader);
    }

    public void goBack(ActionEvent event) throws Exception {
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(GymBuddy.class.getResource("/viewone/launcher/GymFirstView.fxml"));
        stampa(newStage, fxmlLoader);
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    public void stampa(Stage newStage, FXMLLoader fxmlLoader) throws Exception {
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        newStage.setTitle("GymBuddy");
        newStage.setResizable(false);
        newStage.setScene(scene);
        newStage.show();
    }


}
