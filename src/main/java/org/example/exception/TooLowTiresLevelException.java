package org.example.exception;

public class TooLowTiresLevelException extends RuntimeException {

    public TooLowTiresLevelException(String message) {
        super(message);
    }
}
