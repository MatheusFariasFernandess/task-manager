package org.tcc.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class Handler {
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> negocioExceptionHandler(NegocioException e){
       return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorModel()
               .setErrorTime(LocalDateTime.now())
               .setErrorMessage(e.getMessage())
               .setCause("Erro em alguma regra de negocio")
               .setCodigoErro(HttpStatus.CONFLICT.value()
               ));
    }
}
