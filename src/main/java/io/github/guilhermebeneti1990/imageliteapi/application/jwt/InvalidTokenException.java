package io.github.guilhermebeneti1990.imageliteapi.application.jwt;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(String message) {
        super(message);
    }

}
