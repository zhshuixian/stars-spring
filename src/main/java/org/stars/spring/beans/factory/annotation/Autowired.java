package org.stars.spring.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * Marks a constructor, field, setter method or config method as to be
 * autowired by Spring's dependency injection facilities.
 * 将构造函数、字段、setter 方法或者配置方法标记为使用 spring 的依赖注入工具进行自动装配
 *
 * @author : xian
 */
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
}
