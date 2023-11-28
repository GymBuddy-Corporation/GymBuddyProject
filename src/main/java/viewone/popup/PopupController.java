package viewone.popup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class PopupController {
    @FXML
    private Text testo;
    @FXML
    private Button bottone_conferma;
    @FXML
    private Button bottone_rifiuta;

    @FXML
    public void conferma(ActionEvent e){
        PopupGymbuddy.getPopUp().popUpConfirm();
        PopupGymbuddy.getPopUp().clearSingleton();
        //((Node)e.getSource()).getScene().getWindow().hide();
    }
    @FXML
    public void rifiuta(ActionEvent e){
        PopupGymbuddy.getPopUp().popUpDelete();
        PopupGymbuddy.getPopUp().clearSingleton();
        //((Node)e.getSource()).getScene().getWindow().hide();

    }
    public void setValues(String text,String conferma,String rifiuta){
        testo.setText(text);
        bottone_conferma.setText(conferma);
        bottone_rifiuta.setText(rifiuta);
    }
}
