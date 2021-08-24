package org.stars.spring.aop;

import org.stars.spring.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author : xian
 */
@Component("afterAdvice")
public class AopServiceAfterAdvice implements MethodAfterAdvice {
    @Override
    public void after(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("after 拦截方法----" + method.getName());
    }
}
