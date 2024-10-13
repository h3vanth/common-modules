package io.formulate.common.ws.converter;

import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

public class LocalDateToDateConverter implements Converter<LocalDate, Date> {
    @Override
    public Date convert(LocalDate source) {
        return source.toDate();
    }
}
