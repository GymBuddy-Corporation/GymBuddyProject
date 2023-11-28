package exceptions;

import exceptions.MyException;

public abstract class InvalidDataException extends MyException {

    protected InvalidDataException(String title, String header, String content) {
        super(title, header, content);
    }
}
