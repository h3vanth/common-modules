package io.formulate.common.ws.converter;

import org.joda.time.LocalTime;
import org.springframework.core.convert.converter.Converter;

public class LocalTimeToStringConverter implements Converter<LocalTime, String> {
    @Override
    public String convert(LocalTime source) {
        return source.toString("HH:mm");
    }
}
