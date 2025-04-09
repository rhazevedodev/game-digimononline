package br.com.digimon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNaoExisteException extends RuntimeException{

    public UsuarioNaoExisteException(String message) {
        super(message);
    }
}
