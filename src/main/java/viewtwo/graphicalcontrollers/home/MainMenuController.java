package viewtwo.graphicalcontrollers.home;

import engineering.LoggedUserSingleton;
import engineering.UserTypes;
import exceptions.logger.CostumeLogger;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import viewtwo.popups.MenuPopUp;
import viewtwo.popups.abstracts.MenuPopupInterface;

import java.util.NoSuchElementException;

public class MainMenuController implements MenuPopupInterface {
    @FXML
    public ImageView menu;
    @FXML
    Pane paneSfondo;

    public void setActivity(Node e){
        try{
        paneSfondo.getChildren().removeFirst();
        }catch(NoSuchElementException ex){
            CostumeLogger.getInstance().logError(ex);
        }
        paneSfondo.getChildren().add(e);
    }
    @FXML
    void openMenu() {
            if(LoggedUserSingleton.getSingleton().getUserType()== UserTypes.ATHLETE) MenuPopUp.getMenu(this,"MenuPopUpAthlete.fxml","popups",2);
            if(LoggedUserSingleton.getSingleton().getUserType()==UserTypes.PT)MenuPopUp.getMenu(this,"MenuPopUpTrainer.fxml","popups",2);
    }


}
