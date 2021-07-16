package org.stars.spring.aop;

/**
 * 切入点切口，定义用于获取 ClassFilter\MethodMatcher 两个类，则两个接口都市切入点表达提供的内容
 *
 * @author : xian
 */
public interface Pointcut {

    /**
     * 返回切入点的 ClassFilter
     *
     * @return ClassFilter
     */
    ClassFilter getClassFilter();

    /**
     * 返回这个切入点的 MethodMatcher
     *
     * @return MethodMatcher
     */
    MethodMatcher getMethodMatcher();
}
