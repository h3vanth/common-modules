package io.formulate.web.common.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface ExceptionCode {
    String getTemplate();
    HttpStatus getHttpStatus();
    Map<String, ExceptionContextKey> getContext();

    default Map<String, ExceptionContextKey> createContext(ExceptionContextKey... contextKeys) {
        Map<String, ExceptionContextKey> context;
        if (contextKeys != null) {
            context = Stream.of(contextKeys).collect(Collectors.toMap(resolvablePart -> "\\${" + resolvablePart + "}", Function.identity()));
        } else {
            context = Map.of();
        }
        return context;
    }

}
