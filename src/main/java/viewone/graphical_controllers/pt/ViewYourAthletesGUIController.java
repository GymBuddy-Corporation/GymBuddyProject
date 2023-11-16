package viewone.graphical_controllers.pt;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import utils.MainStage;
import utils.SwitchPage;


import java.net.URL;
import java.util.*;

public class ViewYourAthletesGUIController implements Initializable{

    @FXML
    /*private ListView<EventBean> dashboard;

    private final ArrayList<EventBean> elements= new ArrayList<>();*/
    private ListView<String> athletesList;

    /*private void clear(){
        elements.clear();
    }
    private void refresh(){
        dashboard.refresh();
    }*/

    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }
    public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"PTLogin.fxml","launcher",1);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*clear();
        refresh();
        List<EventBean> y;*/
        /*y = new ItemController().item("");*/

        /*while(!(y.isEmpty())){
            EventBean z= y.get(0);
            elements.add(z);
            y.remove(0);
        }*/


        /*dashboard.setItems(FXCollections.observableList(elements));

        dashboard.setCellFactory(param -> new AddAthletesPTListCellFactory());
        dashboard.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            Stage stage = new Stage();
            FXMLLoader root;
            root = new FXMLLoader(Objects.requireNonNull(getClass().getResource("ViewYourAthletes.fxml")));
            String x= elements.get(dashboard.getSelectionModel().getSelectedIndex());

            Scene scene;
            scene = new Scene(root.load(), 417, 359);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
                JoinEventGraphicController o = root.getController();
                o.setDescriptionText(x);
            assert false;

        });*/
        List<String> lista = new ArrayList<>(Arrays.asList("Luca Martorelli", "Matteo Martorelli",
                "Marco Martorelli", "Alexandru Nazare", "Alessandro Lori", "Alessandro Cortese",
                "Giulia Boccuccia", "Andrea De Filippis", "Edoardo Manenti", "Chiara Iurato", "Simone Thanos"));
        athletesList.getItems().addAll(lista);
    }
}
