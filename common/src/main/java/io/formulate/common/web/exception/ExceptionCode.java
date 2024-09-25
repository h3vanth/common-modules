package io.formulate.common.web.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionCode {
    String getTemplate();
    HttpStatus getHttpStatus();
    ExceptionContextKey[] getContextKeys();
}
