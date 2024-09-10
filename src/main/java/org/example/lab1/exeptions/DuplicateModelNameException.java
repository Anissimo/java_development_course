package org.example.lab1.exeptions;

public class DuplicateModelNameException extends RuntimeException {

    public DuplicateModelNameException(String message) {
        super(message);
    }
}
