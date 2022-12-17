package br.com.user.exceptions;

import java.io.Serial;

public class UserException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1671097244785659949L;

    public UserException(String message) {
        super(message);
    }
}
