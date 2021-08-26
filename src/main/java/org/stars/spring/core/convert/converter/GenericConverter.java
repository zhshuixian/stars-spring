package org.stars.spring.core.convert.converter;

import cn.hutool.core.lang.Assert;

import java.util.Set;

/**
 * @author : xian
 */
public interface GenericConverter {

    /**
     * 这个 Converter 支持的所有 sourceType 到 targetType 转换的
     */
    Set<ConvertiblePair> getConvertibleTypes();

    Object convert(Object source, Class<?> sourceType, Class<?> targetType);

    /**
     * sourceType -> targetType 的映射关系
     */
    final class ConvertiblePair {
        private final Class<?> sourceType;

        private final Class<?> targetType;

        public ConvertiblePair(Class<?> sourceType, Class<?> targetType) {
            Assert.notNull(sourceType, "Source type must not be null");
            Assert.notNull(targetType, "Target type must not be null");
            this.sourceType = sourceType;
            this.targetType = targetType;
        }

        public Class<?> getSourceType() {
            return sourceType;
        }

        public Class<?> getTargetType() {
            return targetType;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;

            if (obj == null || obj.getClass() != ConvertiblePair.class) {
                return false;
            }

            ConvertiblePair that = (ConvertiblePair) obj;
            return this.targetType.equals(that.getTargetType()) && this.sourceType.equals(that.getSourceType());
        }

        @Override
        public int hashCode() {
            int result = getSourceType().hashCode();
            result = 31 * result + getTargetType().hashCode();
            return result;
        }
    }
}
