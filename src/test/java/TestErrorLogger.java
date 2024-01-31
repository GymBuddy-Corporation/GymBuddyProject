import exceptions.CostumException;
import exceptions.logger.CostumeLogger;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


class TestErrorLogger {
    @Test
    void test() throws IOException {
        File myObj = new File(CostumeLogger.LOGGINGFILE);
        assert (myObj.delete());
        CostumeLogger.getInstance().logError(new CostumException("Test logger "));
        String a=readFile(CostumeLogger.LOGGINGFILE,Charset.defaultCharset());
        assert(!a.isEmpty());
    }

    static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
