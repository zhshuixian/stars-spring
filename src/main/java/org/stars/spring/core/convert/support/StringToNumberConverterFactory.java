package org.stars.spring.core.convert.support;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import org.stars.spring.core.convert.converter.Converter;
import org.stars.spring.core.convert.converter.ConverterFactory;

/**
 * @author : xian
 */
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {
    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber<>();
    }

    private static final class StringToNumber<T extends Number> implements Converter<String, T> {
        
        @Override
        public T convert(String source) {
            if (StrUtil.isEmpty(source)) {
                return null;
            }

            return (T) NumberUtil.parseNumber(source);
        }
    }
}
