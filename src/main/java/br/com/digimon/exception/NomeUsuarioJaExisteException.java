package br.com.digimon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NomeUsuarioJaExisteException extends RuntimeException {

    public NomeUsuarioJaExisteException(String message) {
        super(message);
    }
}
