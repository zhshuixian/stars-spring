package org.stars.spring.context.annotation;

import java.lang.annotation.*;

/**
 * 作用域的自定义注解，通过 Bean 对象注解的时候，拿到Bean对象的作用域，一般情况下是 singleton
 *
 * @author : xian
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {
    String value() default "singleton";
}
