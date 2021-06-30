package org.stars.spring.beans.factory;

/**
 * 实现此接口，能够感知所属的 bean name
 * @author : xian
 */
public interface BeanNameAware extends Aware{

    /**
     * 设置 bean name
     * @param beanName  bean name
     */
    void setBeanName(String beanName);
}
