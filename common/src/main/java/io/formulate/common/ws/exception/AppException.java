package io.formulate.common.ws.exception;

import io.formulate.common.ws.error.AppError;
import jakarta.ws.rs.core.Response;

public class AppException extends RuntimeException {
    private final Response.Status httpStatus;

    public AppException(String message, Response.Status httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public Response.Status getHttpStatus() {
        return httpStatus;
    }

    public AppError toAppError() {
        return new AppError(getMessage());
    }
}
