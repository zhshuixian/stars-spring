package org.stars.spring.aop;

/**
 * @author : xian
 */
public interface PointCutAdvisor extends Advisor {

    /**
     * 获取这个 Advisor 的切入点
     *
     * @return 切入点
     */
    Pointcut getPointcut();
}
