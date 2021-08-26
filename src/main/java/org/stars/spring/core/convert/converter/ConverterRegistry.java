package org.stars.spring.core.convert.converter;

/**
 * 类型转换接口
 *
 * @author : xian
 */
public interface ConverterRegistry {

    void addConverter(Converter<?, ?> converter);

    void addConverter(GenericConverter converter);

    void addConverterFactory(ConverterFactory<?, ?> converterFactory);
}
