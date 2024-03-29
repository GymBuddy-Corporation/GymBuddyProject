package engineering.popups;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Popup;
import utils.MainStage;
import utils.SwitchPage;

import java.io.IOException;

public  class PopupBaseClass extends Popup{

    protected PopupBaseInterface caller;

    protected static PopupBaseClass popupReference;

    public PopupBaseController getPopupController() {
        return popupController;
    }

    protected PopupBaseController popupController;

    protected PopupBaseClass(PopupBaseInterface instanceOfParent,String file,String folder,int view) throws IOException {
        super();
        this.caller=instanceOfParent;
        String path= SwitchPage.getpath(file,folder,view);
        FXMLLoader fxmlLoader = new FXMLLoader(SwitchPage.class.getResource(path));
        Parent load =fxmlLoader.load();
        this.getContent().add(load);
        ((PopupBaseController)fxmlLoader.getController()).setCaller(this);
        popupController=fxmlLoader.getController();
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
