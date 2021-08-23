package org.stars.spring.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * Annotation at the field or method/constructor parameter level
 * that indicates a default value expression for the affected argument.
 * </p>
 * 字段或者方法参数级别的注解，其表示受影响的参数的默认表达式
 *
 * @author : xian
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {

    /**
     * The actual value expression: 实际的值表达式 e.g. "#{systemProperties.myProp}"
     */
    String value();
}
