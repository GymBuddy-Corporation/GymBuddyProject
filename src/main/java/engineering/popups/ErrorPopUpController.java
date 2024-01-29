package engineering.popups;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;

public class ErrorPopUpController implements Initializable {
    @FXML
    Label errorLabel;
    @FXML
    ProgressBar progress;

    public double progressValue;
    public void setText(String testo){
        errorLabel.setText(testo);
    }

    public void addValue(double value){
            progressValue+=value;
            progress.setProgress(progressValue);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            progress.setProgress(0);
            progressValue=0;
    }
}
