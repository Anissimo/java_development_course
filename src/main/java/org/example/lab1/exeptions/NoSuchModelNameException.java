package org.example.lab1.exeptions;

public class NoSuchModelNameException extends RuntimeException {

    public NoSuchModelNameException(String message) {
        super(message);
    }
}
