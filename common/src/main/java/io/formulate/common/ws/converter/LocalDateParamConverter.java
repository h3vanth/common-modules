package io.formulate.common.ws.converter;

import jakarta.ws.rs.ext.ParamConverter;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

public class LocalDateParamConverter implements ParamConverter<LocalDate> {
    @Override
    public LocalDate fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        return LocalDate.parse(value, DateTimeFormat.forPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString(LocalDate value) {
        return value.toString("yyyy-MM-dd");
    }
}
