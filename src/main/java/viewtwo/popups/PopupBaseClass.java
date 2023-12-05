package viewtwo.popups;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Popup;
import utils.MainStage;
import utils.SwitchPage;
import viewtwo.popups.abstracts.PopupBaseInterface;
import viewtwo.popups.controllers.PopupBaseController;

import java.io.IOException;

public  class PopupBaseClass extends Popup{

    PopupBaseInterface caller;

    protected static PopupBaseClass popupReference;


    protected PopupBaseClass(PopupBaseInterface instanceOfParent,String file,String folder,int view) throws IOException {
        super();
        this.caller=instanceOfParent;
        String path= SwitchPage.getpath(file,folder,view);
        FXMLLoader fxmlLoader = new FXMLLoader(SwitchPage.class.getResource(path));
        Parent load = null;
        load = fxmlLoader.load();
        this.getContent().add(load);
        ((PopupBaseController)fxmlLoader.getController()).setCaller(this);
        this.show(MainStage.getStage());
        this.setAutoHide(true);
        this.setOnAutoHide(handler->{clearSingleton();});
    }


    public static void clearSingleton(){
        popupReference.caller=null;
        popupReference=null;
    }
    public void hidePopUp(){
        popupReference.hide();
        clearSingleton();
    }
}
