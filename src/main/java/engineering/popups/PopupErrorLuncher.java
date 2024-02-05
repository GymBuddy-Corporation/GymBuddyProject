package engineering.popups;

import exceptions.CostumException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Popup;
import javafx.util.Duration;
import utils.MainStage;
import utils.SwitchPage;
import javafx.animation.PauseTransition;
import java.io.IOException;

public class PopupErrorLuncher  extends Popup {
    private PopupErrorLuncher(CostumException e,int view) throws IOException {
        super();
        String path= SwitchPage.getpath("ErrorPopup.fxml","popups",view);
        FXMLLoader fxmlLoader = new FXMLLoader(SwitchPage.class.getResource(path));
        Parent load;
        load = fxmlLoader.load();
       this.setAnchorX(MainStage.getStage().getX());
        this.setAnchorY(MainStage.getStage().getY());
        this.getContent().add(load);
        ((ErrorPopUpController)fxmlLoader.getController()).setText(e.getMessage());
        this.show(MainStage.getStage());

        for(int i=1;i<=100;i++){
            PauseTransition pause = new PauseTransition(Duration.seconds(0.03*i));
            pause.setOnFinished(k ->  ((ErrorPopUpController)fxmlLoader.getController()).addValue(0.01));
            pause.play();
        }
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(k ->  this.hide());
        pause.play();

    }

    public static void getErrorPopup(CostumException e,int view) throws IOException {
        new PopupErrorLuncher(e,view);
    }





}
