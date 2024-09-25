package io.formulate.common.web.exception;

import io.formulate.common.web.error.AppError;
import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {
    private final HttpStatus httpStatus;

    public AppException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public AppError toAppError() {
        return new AppError(getMessage());
    }
}
