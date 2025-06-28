package org.tcc.api.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.tcc.api.utils.DateUtils;

import java.time.LocalDateTime;

public class ErrorModel {
    public String errorMessage;
    public String cause;
    @JsonFormat(pattern = DateUtils.DATA_HORA, shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "America/Fortaleza")
    public LocalDateTime errorTime;
    public int codigoErro;
    public String getErrorMessage() {
        return errorMessage;
    }

    public ErrorModel setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public String getCause() {
        return cause;
    }

    public ErrorModel setCause(String cause) {
        this.cause = cause;
        return this;
    }

    public LocalDateTime getErrorTime() {
        return errorTime;
    }

    public ErrorModel setErrorTime(LocalDateTime errorTime) {
        this.errorTime = errorTime;
        return this;
    }

    public int getCodigoErro() {
        return codigoErro;
    }

    public ErrorModel setCodigoErro(int codigoErro) {
        this.codigoErro = codigoErro;
        return this;
    }
}
