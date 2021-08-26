package org.stars.spring.core.converter;

import org.stars.spring.core.convert.converter.Converter;

/**
 * @author : xian
 */
public class StringToIntegerConverter implements Converter<String, Integer> {
    @Override
    public Integer convert(String source) {
        return Integer.valueOf(source);
    }
}
