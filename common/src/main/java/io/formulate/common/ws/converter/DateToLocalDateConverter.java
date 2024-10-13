package io.formulate.common.ws.converter;

import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

public class DateToLocalDateConverter implements Converter<Date, LocalDate> {
    @Override
    public LocalDate convert(Date source) {
        return LocalDate.fromDateFields(source);
    }
}
