package io.formulate.common.ws.exception;

import jakarta.ws.rs.core.Response;

public interface ExceptionCode {
    String getTemplate();

    Response.Status getHttpStatus();

    ExceptionContextKey[] getContextKeys();
}
