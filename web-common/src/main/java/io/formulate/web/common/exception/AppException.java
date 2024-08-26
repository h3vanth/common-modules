package io.formulate.web.common.exception;

import io.formulate.web.common.error.AppError;
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

    public AppError toApplicationError() {
        return new AppError(this.getMessage());
    }
}
