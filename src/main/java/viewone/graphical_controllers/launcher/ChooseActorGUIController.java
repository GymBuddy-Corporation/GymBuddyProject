package viewone.graphical_controllers.launcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewone.GymBuddy;

import java.net.URL;
import java.util.EnumMap;
import java.util.EventObject;
import java.util.ResourceBundle;


public class ChooseActorGUIController implements Initializable {

    @FXML private ImageView gym;
    @FXML private ImageView pt;
    @FXML private ImageView user;
//TODO: devo trovare una solzione per i radio button sylarli con il css oppure non so
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            empty=new Image(ChooseActorGUIController.class.getResourceAsStream("/viewone/images/LogoEmptyButton96.png"),20,20,false,true);
            full=new Image(ChooseActorGUIController.class.getResourceAsStream("/viewone/images/LogoFullButton96.png"),20,20,false,true);


    }

    enum Selezione{
        GYM,
        PT,
        USER
    }

    Selezione scelta;
    Image empty,full;


    private void resetButtons(){
        gym.setImage(empty);
        pt.setImage(empty);
        user.setImage(empty);
    }
@FXML
    public void setBottonegym()throws  Exception{
        pt.setImage(empty);
        gym.setImage(full);
        user.setImage(empty);
        scelta=Selezione.GYM;
    }
    @FXML
    public void setBottonept()throws  Exception{
        pt.setImage(full);
        gym.setImage(empty);
        user.setImage(empty);
        scelta=Selezione.PT;
    }
    @FXML
    public void setBottoneuser()throws  Exception{
        pt.setImage(empty);
        gym.setImage(empty);
        user.setImage(full);
        scelta=Selezione.USER;
    }
    @FXML
    public void goForward(ActionEvent event) throws Exception {
        if(scelta==Selezione.GYM) {
            String path = "/viewone/launcher/GymFirst.fxml";
            stampa(path, event);
        } else if (scelta==Selezione.PT) {
            String path = "/viewone/launcher/PTLogin.fxml";
            stampa(path, event);
        } else if(scelta==Selezione.USER) {
            String path = "/viewone/launcher/ChooseActor.fxml";
            stampa(path, event);
        }
    }

    public void changeLanguage(MouseEvent event) {
        //TODO gestisci il bottone con il pop sulla scelta della lingua (il cambio è dummy)
    }

    public void stampa(String path, ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setTitle("GymBuddy");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}

