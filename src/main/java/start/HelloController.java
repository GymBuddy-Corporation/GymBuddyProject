package start;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label prova;

    @FXML
    protected void onHelloButtonClick() {
        prova.setText("");
    }
}