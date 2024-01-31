package exceptions.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CostumeLogger {
    private final  Logger logger = Logger.getLogger(CostumeLogger.class.getName());
    private  FileHandler fileHandler = null;

    private static CostumeLogger me;
    protected  CostumeLogger(){
        try {
            fileHandler=new FileHandler("loggerExample.log", false);
        } catch (SecurityException | IOException e) {
            return;
        }
        Logger l = Logger.getLogger("");
        fileHandler.setFormatter(new SimpleFormatter());
        l.addHandler(fileHandler);
        l.setLevel(Level.CONFIG);
    }

    public static CostumeLogger getInstance(){
        if(me!=null && me.fileHandler!=null){
            return me;
        }else if(me!=null) reset();
        me=new CostumeLogger();
        return me;
    }
    public static void reset(){
        if(me!=null && me.fileHandler!=null) {
            me.logger.removeHandler(me.fileHandler);
            me=null;
        }
    }
    public void logError(String message){
        if(fileHandler!=null) logger.log(Level.WARNING,message);
    }
}
