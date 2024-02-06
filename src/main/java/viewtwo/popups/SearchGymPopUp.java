package viewtwo.popups;

import beans.GymInfoBean;
import engineering.popups.PopupBaseClass;
import viewtwo.popups.abstracts.SearchGymInterface;
import viewtwo.popups.controllers.SearchGymPopUpController;

import java.io.IOException;
import java.util.List;

public class SearchGymPopUp extends PopupBaseClass {
    private final SearchGymInterface castedInterface;
    protected SearchGymPopUp(SearchGymInterface instanceOfParent, String file, String folder, int view) throws IOException {
        super(instanceOfParent, file, folder, view);
        castedInterface=(SearchGymInterface) this.caller;
    }
    public static PopupBaseClass getSearchPopUp(SearchGymInterface caller, List<GymInfoBean> beans, String file, String folder, int view) throws IOException {
        if(PopupBaseClass.popupReference==null){
            PopupBaseClass.popupReference=new SearchGymPopUp(caller,file,folder,view);
            ((SearchGymPopUpController)popupReference.getPopupController()).setBean(beans);
        }
        return PopupBaseClass.popupReference;
    }

    public void selectedGym(GymInfoBean bean){
            castedInterface.searchResult(bean);
            hidePopUp();
    }
}
