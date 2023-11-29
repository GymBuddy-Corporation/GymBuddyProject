package viewone.popup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class PopupController {
    @FXML
    private Text testo;
    @FXML
    private Button bottoneConferma;
    @FXML
    private Button bottoneRifiuta;

    @FXML
    public void conferma(ActionEvent e){
        PopupGymbuddy.getPopUp().popUpConfirm();
        PopupGymbuddy.getPopUp().hidePopUp();
        PopupGymbuddy.clearSingleton();
    }
    @FXML
    public void rifiuta(ActionEvent e){
        PopupGymbuddy.getPopUp().popUpDelete();
        PopupGymbuddy.clearSingleton();
    }
    public void setValues(String text,String conferma,String rifiuta){
        testo.setText(text);
        bottoneConferma.setText(conferma);
        bottoneRifiuta.setText(rifiuta);
    }
}
