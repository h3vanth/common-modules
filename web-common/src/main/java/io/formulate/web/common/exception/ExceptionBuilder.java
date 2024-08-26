package io.formulate.web.common.exception;

import com.google.common.base.Preconditions;

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
        for (Map.Entry<String, ExceptionContextKey> contextEntry : code.getContext().entrySet()) {
            String value = contextMap.get(contextEntry.getValue());
            if (value != null) {
                template = template.replaceAll(contextEntry.getKey(), contextMap.get(contextEntry.getValue()));
            }
        }
        return new AppException(template, code.getHttpStatus());
    }
}
