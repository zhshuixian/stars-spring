package org.stars.spring.aop;

/**
 * 定义类的匹配类，用于切点找到给定的接口或者目标类
 *
 * @author : xian
 */
public interface ClassFilter {

    /**
     * 指定的切入点是否适用于指定的接口或者目标的类
     * Should the pointcut apply to the given interface or target class?
     *
     * @param clazz the candidate target class 候选的目标类
     * @return whether the advice should apply to the given target class 是否使用用指定的目标类
     */
    boolean matches(Class<?> clazz);
}
