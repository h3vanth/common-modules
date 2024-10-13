package io.formulate.common.ws.converter;

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import org.joda.time.LocalDate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class AppParamConverterProvider implements ParamConverterProvider {
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        if (rawType.equals(LocalDate.class)) {
            return (ParamConverter<T>) new LocalDateParamConverter();
        }
        return null;
    }
}

