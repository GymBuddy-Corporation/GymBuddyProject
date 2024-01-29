package viewone.popup;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class PopupController {
    @FXML
    private Text testo;
    @FXML
    private Button bottoneConferma;
    @FXML
    private Button bottoneRifiuta;

    @FXML
    public void conferma() throws IOException {
        PopupGymbuddy.getPopUp().popUpConfirm();
        PopupGymbuddy.getPopUp().hidePopUp();
        PopupGymbuddy.clearSingleton();
    }
    @FXML
    public void rifiuta() throws IOException{
        PopupGymbuddy.getPopUp().popUpDelete();
        PopupGymbuddy.clearSingleton();
    }
    public void setValues(String text,String conferma,String rifiuta){
        testo.setText(text);
        bottoneConferma.setText(conferma);
        bottoneRifiuta.setText(rifiuta);
    }
}
