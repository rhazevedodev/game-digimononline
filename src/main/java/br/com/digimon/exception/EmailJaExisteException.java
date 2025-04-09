package br.com.digimon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailJaExisteException extends RuntimeException {

    public EmailJaExisteException(String message) {
        super(message);
    }
}
