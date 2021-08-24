package org.stars.spring.aop.framework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.stars.spring.aop.MethodBeforeAdvice;

/**
 * @author : xian
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

    private MethodBeforeAdvice advice;


    public MethodBeforeAdviceInterceptor() {
    }

    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice beforeAdvice) {
        this.advice = beforeAdvice;

    }


    public void setAdvice(MethodBeforeAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        // MethodBeforeAdviceInterceptor 实现了 MethodInterceptor 接口，
        // 在 invoke 方法中调用 advice 中的 before 方法，传入对应的参数信息。
        // 这个 advice.before 则是用于自己实现 MethodBeforeAdvice 接口后做的相应处理
        this.advice.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        return methodInvocation.proceed();
    }
}
