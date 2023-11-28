package exceptions;

import java.util.Arrays;
import java.util.List;

public class MyException extends Exception{
    protected final String title;
    protected final String header;
    protected final String content;

    protected MyException(String title, String header, String content) {
        this.title = title;
        this.header = header;
        this.content = content;
    }

    public List<String> getErrorStrings() {
        return Arrays.asList(title, header, content);
    }
}
