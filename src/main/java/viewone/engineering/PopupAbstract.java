package viewone.engineering;

import java.io.IOException;

public abstract class PopupAbstract {

    private PopupGymbuddy temp;

    private void popUpCreate(String testo,String conferma,String rifiuta){

    }
    private void popUpCreate(PopupAbstract me, String testo) throws IOException {
       this.temp= PopupGymbuddy.startPopUp(me,testo);
    }
    public abstract void popUpConfirm();
    public abstract void popUpDelete();
}
