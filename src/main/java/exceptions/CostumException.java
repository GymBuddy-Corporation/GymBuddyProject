package exceptions;

import engineering.popups.PopupErrorLuncher;
import exceptions.logger.CostumeLogger;

import java.io.IOException;

public class CostumException extends Exception{
    public CostumException(String message){
        super(message);
    }

    public CostumException(String message,Throwable invocationCause){
        super(message,invocationCause);
    }
    public void callMe(int view){
        try {
            PopupErrorLuncher.getErrorPopup(this,view);
        } catch (IOException e) {
            CostumeLogger.getInstance().logError(e);
        }
    }
}
