package viewtwo.popups;


import beans.MembershipBean;
import engineering.popups.PopupBaseClass;
import viewtwo.popups.abstracts.SearchMembershipInterface;
import viewtwo.popups.controllers.SearchMembershipPopUpController;

import java.io.IOException;
import java.util.List;

public class SearchMembershipPopUp extends PopupBaseClass {
    private final SearchMembershipInterface castedInterface;
    protected SearchMembershipPopUp(SearchMembershipInterface instanceOfParent, String file, String folder, int view) throws IOException {
        super(instanceOfParent, file, folder, view);
        castedInterface=(SearchMembershipInterface) this.caller;
    }
    public static PopupBaseClass getSearchPopUp(SearchMembershipInterface caller, List<MembershipBean> beans, String file, String folder, int view) throws IOException {
        if(PopupBaseClass.popupReference==null){
            PopupBaseClass.popupReference=new SearchMembershipPopUp(caller,file,folder,view);
            ((SearchMembershipPopUpController)popupReference.getPopupController()).setBean(beans);
        }
        return PopupBaseClass.popupReference;
    }

    public void selectMembership(MembershipBean bean){
        castedInterface.selectedMembership(bean);
        hidePopUp();
    }

}
