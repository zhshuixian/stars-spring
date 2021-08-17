package org.stars.spring.stereotype;

import java.lang.annotation.*;

/**
 * 配置到 class 类上的，类似的还有 Service Controller
 *
 * @author : xian
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
    String value() default "";
}
