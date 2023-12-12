package exceptions;

import engineering.popups.PopupErrorLuncher;

import java.io.IOException;

public class CostumException extends Exception{
    public CostumException(String message){
        super(message);
    }

    public CostumException(String message,Throwable invocationCause){
        super(message,invocationCause);
    }
    public void callMe(int view) throws IOException {
        PopupErrorLuncher.getErrorPopup(this,view);
    }
}
