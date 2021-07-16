package org.stars.spring.aop;

import java.lang.reflect.Method;

/**
 * 方法匹配，找到表达式范围下匹配的目标类和方法
 *
 * @author : xian
 */
public interface MethodMatcher {

    /**
     * 检测给定的方法是否匹配
     * Perform static checking whether the given method matches. If this
     *
     * @param method      目标的方法
     * @param targetClass 目标类
     * @return whether or not this method matches statically 此方法是否静态匹配
     */
    boolean matches(Method method, Class<?> targetClass);
}
