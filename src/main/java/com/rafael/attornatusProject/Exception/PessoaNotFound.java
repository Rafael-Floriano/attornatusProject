package com.rafael.attornatusProject.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PessoaNotFound extends RuntimeException{
    public PessoaNotFound(String mensagem) {
        super(mensagem);
    }
}
