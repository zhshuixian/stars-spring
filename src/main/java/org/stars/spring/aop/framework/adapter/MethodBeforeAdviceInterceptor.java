package org.stars.spring.aop.framework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.stars.spring.aop.MethodAfterAdvice;
import org.stars.spring.aop.MethodBeforeAdvice;

/**
 * @author : xian
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

    private MethodBeforeAdvice beforeAdvice;

    private MethodAfterAdvice afterAdvice;

    public MethodBeforeAdviceInterceptor() {
    }

    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice beforeAdvice, MethodAfterAdvice afterAdvice) {
        this.beforeAdvice = beforeAdvice;
        this.afterAdvice = afterAdvice;
    }

    public void setAfterAdvice(MethodAfterAdvice afterAdvice) {
        this.afterAdvice = afterAdvice;
    }

    public void setBeforeAdvice(MethodBeforeAdvice beforeAdvice) {
        this.beforeAdvice = beforeAdvice;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        // MethodBeforeAdviceInterceptor 实现了 MethodInterceptor 接口，
        // 在 invoke 方法中调用 advice 中的 before 方法，传入对应的参数信息。
        // 这个 advice.before 则是用于自己实现 MethodBeforeAdvice 接口后做的相应处理
        this.beforeAdvice.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        Object res = methodInvocation.proceed();
        afterAdvice.after(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        return res;
    }
}
