package viewone.engineering;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Popup_controller {
    @FXML
    private Text testo;
    @FXML
    private Button bottone_conferma;
    @FXML
    private Button bottone_rifiuta;

    @FXML
    public void conferma(ActionEvent e){
        Popup_gymbuddy.getPopUp().popUpConfirm();
        Popup_gymbuddy.getPopUp().clearSingleton();
        //((Node)e.getSource()).getScene().getWindow().hide();
    }
    @FXML
    public void rifiuta(ActionEvent e){
        Popup_gymbuddy.getPopUp().popUpDelete();
        Popup_gymbuddy.getPopUp().clearSingleton();
        //((Node)e.getSource()).getScene().getWindow().hide();

    }
    public void setValues(String text,String conferma,String rifiuta){
        testo.setText(text);
        bottone_conferma.setText(conferma);
        bottone_rifiuta.setText(rifiuta);
    }
}
