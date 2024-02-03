package com.algaworks.algatransito.domain.exception;
                                        //RuntimeException nao precisa ser tratado agora
public class NegocioException extends RuntimeException {

    public NegocioException(String message) {
            super(message);
    }
}
