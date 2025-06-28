package org.tcc.api.exceptions;

public class NegocioException extends RuntimeException{
    public NegocioException (String message){
        super(message);
    }
}
