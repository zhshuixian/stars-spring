package org.stars.spring.aop;

import org.stars.spring.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author : xian
 */
@Component("beforeAdvice")
public class AopServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法---" + method.getName());
    }
}
