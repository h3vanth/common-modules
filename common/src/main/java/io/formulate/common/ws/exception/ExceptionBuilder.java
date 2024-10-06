package io.formulate.common.ws.exception;

import com.google.common.base.Preconditions;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

public class ExceptionBuilder {
    private ExceptionCode code;
    private Map<ExceptionContextKey, String> contextMap;

    public ExceptionBuilder(ExceptionCode code) {
        this.code = Preconditions.checkNotNull(code);
        contextMap = new HashMap<>();
    }

    public ExceptionBuilder(ExceptionCode code, Map<ExceptionContextKey, String> contextMap) {
        this.code = Preconditions.checkNotNull(code);
        this.contextMap = Preconditions.checkNotNull(contextMap);
    }

    public ExceptionBuilder put(ExceptionContextKey contextKey, String value) {
        contextMap.put(Preconditions.checkNotNull(contextKey), Preconditions.checkNotNull(value));
        return this;
    }

    public ExceptionBuilder putAll(Map<ExceptionContextKey, String> contextMap) {
        this.contextMap.putAll(Preconditions.checkNotNull(contextMap));
        return this;
    }

    public static ExceptionBuilder exception(ExceptionCode code) {
        return new ExceptionBuilder(code);
    }

    public static ExceptionBuilder exception(ExceptionCode code, Map<ExceptionContextKey, String> contextMap) {
        return new ExceptionBuilder(code, contextMap);
    }

    public AppException build() {
        String template = code.getTemplate();
        Response.Status httpStatus = code.getHttpStatus();
        ExceptionContextKey[] contextKeys = code.getContextKeys();
        if (contextKeys == null || contextKeys.length == 0) {
            return new AppException(template, httpStatus);
        }
        for (ExceptionContextKey contextKey : contextKeys) {
            String value = contextMap.get(contextKey);
            if (value != null) {
                template = template.replaceAll("\\$\\{" + contextKey + "\\}", value);
            }
        }
        return new AppException(template, httpStatus);
    }
}
