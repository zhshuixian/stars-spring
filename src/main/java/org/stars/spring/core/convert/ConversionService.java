package org.stars.spring.core.convert;

import org.jetbrains.annotations.Nullable;

/**
 * @author : xian
 */
public interface ConversionService {

    /**
     * 判断 sourceType 是否可以转换为 targetType
     *
     * @param sourceType
     * @param targetType
     * @return
     */
    boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType);

    /**
     * source 转换为 target
     *
     * @param source
     * @param targetType
     * @param <T>
     * @return
     */
    <T> T convert(Object source, Class<T> targetType);

}
