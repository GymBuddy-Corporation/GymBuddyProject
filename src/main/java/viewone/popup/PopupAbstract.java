package viewone.popup;

import java.io.IOException;

public abstract class PopupAbstract {

    private PopupGymbuddy temp;

    protected void popUpCreate(String testo,String conferma,String rifiuta) throws IOException {
        this.temp=PopupGymbuddy.startPopUp(this,testo,conferma,rifiuta);
    }
    protected void popUpCreate(String testo) throws IOException {
       this.temp= PopupGymbuddy.startPopUp(this,testo);
    }
    public abstract void popUpConfirm();
    public abstract void popUpDelete();
}
