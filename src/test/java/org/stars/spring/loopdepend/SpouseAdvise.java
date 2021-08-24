package org.stars.spring.loopdepend;

import org.stars.spring.aop.MethodBeforeAdvice;
import org.stars.spring.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author : xian
 */
@Component("beforeAdvice")
public class SpouseAdvise implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("SpouseAdvise 拦截切面" + method);
    }
}
