package org.stars.spring.aop;

import org.aopalliance.aop.Advice;

/**
 * Advisor 访问者:
 * Advisor 承担了 Pointcut 和 Advice 的组合，Pointcut 用于获取 JoinPoint，而 Advice 决定于 JoinPoint 执行什么操作
 *
 * @author : xian
 */
public interface Advisor {

    /**
     * 返回切面的 Advice， Advice 可以被拦截器拦截，BeforeAdvice 、ThrowsAdvice 等等
     * Return the advice part of this aspect，An advice may be an
     * interceptor, a before advice, a throws adv  ice, etc.
     *
     * @return the advice that should apply if the pointcut matches：匹配的切入点的 Advice
     * @see org.aopalliance.intercept.MethodInterceptor
     * @see BeforeAdvice
     */
    Advice getAdvice();
}
