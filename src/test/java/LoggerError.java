import exceptions.CostumException;
import exceptions.logger.CostumeLogger;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class LoggerError {
    @Test
    public void test(){
        try{
            throw new CostumException("Test logger ");
        }catch (CostumException e){
            CostumeLogger.getInstance().logError(e);
        }
    }
}
