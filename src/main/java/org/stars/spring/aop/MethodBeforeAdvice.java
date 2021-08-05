package org.stars.spring.aop;

import java.lang.reflect.Method;

/**
 * @author : xian
 */
public interface MethodBeforeAdvice extends BeforeAdvice {

    /**
     * callback before a give method is invoked
     * 指定方法执行前的回调
     *
     * @param method 方法
     * @param args   方法参数
     * @param target 执行这个方法的目标对象，  可能为空
     * @throws Throwable if this object wishes to abort the call.
     *                   Any exception thrown will be returned to the caller if it's
     *                   allowed by the method signature. Otherwise the exception
     *                   will be wrapped as a runtime exception.
     */
    void before(Method method, Object[] args, Object target) throws Throwable;
}
