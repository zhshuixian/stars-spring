package org.stars.spring.core.convert.converter;

/**
 * 类型转换工厂
 *
 * @author : xian
 */
public interface ConverterFactory<S, R> {

    /**
     * 当 T instance of R 时候，获得 S 转为 T 的 converter
     *
     * @param targetType targetType the target to convert to
     * @param <T>        targetType
     * @return S 转为 T 的 converter
     */
    <T extends R> Converter<S, T> getConverter(Class<T> targetType);
}
