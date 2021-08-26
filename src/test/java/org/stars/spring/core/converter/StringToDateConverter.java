package org.stars.spring.core.converter;

import org.stars.spring.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author : xian
 */
public class StringToDateConverter implements Converter<String, LocalDate> {

    private final DateTimeFormatter formatter;

    public StringToDateConverter(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public LocalDate convert(String source) {
        return LocalDate.parse(source, formatter);
    }
}
