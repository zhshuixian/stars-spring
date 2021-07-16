package org.stars.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author : xian
 */
public class AopServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return methodInvocation.proceed();
        } finally {
            System.out.println("AOP 监控开始 -----");
            System.out.println("方法名称：" + methodInvocation.getMethod());
            System.out.println("执行耗时：" + (System.currentTimeMillis() - start) + "ms");
            System.out.println("AOP 监控结束 ----");
        }
    }
}
