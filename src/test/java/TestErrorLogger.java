import exceptions.CostumException;
import exceptions.logger.CostumeLogger;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;


class TestErrorLogger {
    /*Test del alunno Alexandru Nazare 0307030*/
    @Test
    void test() throws IOException {
        File myObj = new File(CostumeLogger.LOGGINGFILE);
        assert (myObj.delete());
        CostumeLogger.getInstance().logError(new CostumException("Test logger "));
        String a=readFile(CostumeLogger.LOGGINGFILE,Charset.defaultCharset());
        assertFalse(a.isEmpty());
    }

    static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
