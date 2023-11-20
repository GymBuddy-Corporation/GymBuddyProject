package viewone.engineering;

public abstract class Popup_abstract {

    private Popup_gymbuddy temp;

    private void popUpCreate(String testo,String conferma,String rifiuta){

    }
    private void popUpCreate(Popup_abstract me,String testo){
       this.temp=Popup_gymbuddy.startPopUp(me,testo);
    }
    public abstract void popUpConfirm();
    public abstract void popUpDelete();
}
