package org.stars.spring.core.convert.converter;

/**
 * 类型转换接口
 *
 * @author : xian
 */
public interface Converter<S, T> {

    /**
     * S 转换为 T 类型
     *
     * @param source 原类型
     * @return 转换后的类型
     */
    T convert(S source);
}
